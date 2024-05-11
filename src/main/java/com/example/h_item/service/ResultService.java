package com.example.h_item.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.example.h_item.cache.LoginUtil;
import com.example.h_item.cache.UserLoginCache;
import com.example.h_item.common.Pager;
import com.example.h_item.enums.FundStatusEnum;
import com.example.h_item.enums.ResultStatusEnum;
import com.example.h_item.enums.ResultTypeEnum;
import com.example.h_item.mapper.ProjectMapper;
import com.example.h_item.mapper.ResultMapper;
import com.example.h_item.model.dto.ResultAnalysisDTO;
import com.example.h_item.model.po.FundPO;
import com.example.h_item.model.po.ProjectPO;
import com.example.h_item.model.po.ResultPO;
import com.example.h_item.model.po.UserPO;
import com.example.h_item.model.req.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResultService {

    @Resource
    private ResultMapper resultMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private UserLoginCache userLoginCache;

    @Resource
    private UserService userService;

    public int insert(ResultPO resultPO) {
        return resultMapper.insert(resultPO);
    }


    public int update(ResultPO resultPO) {
        return resultMapper.update(resultPO);
    }

    public ResultPO queryById(long id) {
        return resultMapper.queryById(id);
    }

    public Pager<ResultPO> queryPager(ResultReq req) {
        List<ResultPO> data = resultMapper.queryPager(req);
        return new Pager<>(new Pager.PageData(req.getPage(), resultMapper.count(req)),data);
    }

    public void apply(ResultApplyReq req) {
        ProjectPO projectPO = projectMapper.queryById(req.getProjectId());
        Assert.notNull(projectPO, "找不到项目信息");

        Long userId = userLoginCache.getUserIdByToken(LoginUtil.get());
        UserPO userPO = userService.queryById(userId);
        Assert.notNull(userPO, "找不到用户信息");

        ResultPO resultPO = new ResultPO();
        resultPO.setUserId(userId);
        resultPO.setAcademyId(userPO.getAcademyId());
        resultPO.setProName(projectPO.getProName());
        resultPO.setRemark(req.getRemark());
        resultPO.setResultType(req.getResultType());
        resultPO.setStatus(ResultStatusEnum.WAIT_AUDIT.name());
        resultMapper.insert(resultPO);
    }

    public void cancel(IdRequest req) {
        ResultPO resultPO = resultMapper.queryById(req.getId());
        Assert.notNull(resultPO, "找不到对应的成果信息");
        Assert.isTrue(StrUtil.equals(resultPO.getStatus(), FundStatusEnum.WAIT_AUDIT.name()),
                "待审核的经费申请才能取消");
        resultPO.setStatus(FundStatusEnum.CANCEL.name());
        resultMapper.update(resultPO);
    }

    public void audit(ResultAuditReq req) {
        ResultPO resultPO = resultMapper.queryById(req.getResultId());
        Assert.notNull(resultPO, "找不到对应的成果信息");
        Assert.isTrue(StrUtil.equals(resultPO.getStatus(), FundStatusEnum.WAIT_AUDIT.name()),
                "待审核的成果申请才能审核");
        resultPO.setStatus(req.getAcceptFlag() ? FundStatusEnum.ACCEPT.name() : FundStatusEnum.REFUSE.name());
        resultMapper.update(resultPO);
    }

    public ResultAnalysisDTO resultAnalysis() {
        Long userId = userLoginCache.getUserIdByToken(LoginUtil.get());
        UserPO userPO = userService.queryById(userId);
        Assert.notNull(userPO, "找不到用户信息");

        ResultReq req = new ResultReq();
        req.setStartTime(DateUtil.beginOfYear(new Date()));
        req.setEndTime(DateUtil.endOfYear(new Date()));
        req.setStatus(ResultStatusEnum.ACCEPT.name());
        if (userPO.getRoleId() != 3) {
            req.setAcademyId(userPO.getAcademyId());
        }
        List<ResultPO> resultPOS = resultMapper.queryAll(req);
        ResultAnalysisDTO resultAnalysisDTO = new ResultAnalysisDTO();
        Map<String, List<ResultPO>> resultTypeMap = resultPOS.stream().collect(Collectors.groupingBy(ResultPO::getResultType));
        // 类型
        List<ResultAnalysisDTO.ResultTypeAnalysis> resultTypeAnalysisList = new ArrayList<>();
        for (Map.Entry<String, List<ResultPO>> entry : resultTypeMap.entrySet()) {
            ResultAnalysisDTO.ResultTypeAnalysis resultTypeAnalysis = new ResultAnalysisDTO.ResultTypeAnalysis();
            resultTypeAnalysis.setCnt(entry.getValue().size());
            resultTypeAnalysis.setResultType(entry.getKey());
            resultTypeAnalysis.setResultTypeName(ResultTypeEnum.valueOf(entry.getKey()).getDesc());
            resultTypeAnalysisList.add(resultTypeAnalysis);
        }
        resultAnalysisDTO.setResultTypeAnalyses(resultTypeAnalysisList);

        // 季度
        Map<Integer, List<ResultPO>> quarterMap = resultPOS.stream().collect(Collectors.groupingBy(x -> DateTime.of(x.getCreateTime()).quarter()));
        List<ResultAnalysisDTO.ResultQuarterAnalysis> resultQuarterAnalysisList = new ArrayList<>();
        for (Map.Entry<Integer, List<ResultPO>> entry : quarterMap.entrySet()) {
            ResultAnalysisDTO.ResultQuarterAnalysis resultQuarterAnalysis = new ResultAnalysisDTO.ResultQuarterAnalysis();
            resultQuarterAnalysis.setCnt(entry.getValue().size());
            resultQuarterAnalysis.setQuarter(StrUtil.format("第{}季度",entry.getKey()));
            resultQuarterAnalysisList.add(resultQuarterAnalysis);
        }
        resultAnalysisDTO.setResultQuarterAnalyses(resultQuarterAnalysisList);

        // 学院 + 月份
        // 学院 -> 成果
        List<ResultAnalysisDTO.ResultAcademyAnalysis> resultAcademyAnalysisList = new ArrayList<>();
        Map<Long, List<ResultPO>> academyMap = resultPOS.stream().collect(Collectors.groupingBy(ResultPO::getAcademyId));
        for (Map.Entry<Long, List<ResultPO>> academyEntry : academyMap.entrySet()) {
            Long academyId = academyEntry.getKey();
            List<ResultPO> academyResult = academyEntry.getValue();
            Map<Integer, List<ResultPO>> monthResultMap = academyResult.stream().collect(Collectors.groupingBy(x -> DateTime.of(x.getCreateTime()).month()));
            List<Integer> monthCnt = new ArrayList<>(12);
            for (int i = 0; i < 12; i++) {
                monthCnt.add(0);
            }
            for (Map.Entry<Integer, List<ResultPO>> monthResultEntry : monthResultMap.entrySet()) {
                monthCnt.set(monthResultEntry.getKey(),monthResultEntry.getValue().size());
            }

            ResultAnalysisDTO.ResultAcademyAnalysis resultAcademyAnalysis = new ResultAnalysisDTO.ResultAcademyAnalysis();
            resultAcademyAnalysis.setAcademyId(academyId);
            resultAcademyAnalysis.setMonthCntList(monthCnt);
            resultAcademyAnalysisList.add(resultAcademyAnalysis);
        }
        resultAnalysisDTO.setResultAcademyAnalyses(resultAcademyAnalysisList);

        return resultAnalysisDTO;
    }
}
