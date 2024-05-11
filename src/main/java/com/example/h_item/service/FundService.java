package com.example.h_item.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.example.h_item.cache.LoginUtil;
import com.example.h_item.cache.UserLoginCache;
import com.example.h_item.common.Pager;
import com.example.h_item.enums.FundStatusEnum;
import com.example.h_item.mapper.FundMapper;
import com.example.h_item.model.po.FundPO;
import com.example.h_item.model.po.ProjectPO;
import com.example.h_item.model.po.UserPO;
import com.example.h_item.model.req.FundApplyReq;
import com.example.h_item.model.req.FundAuditReq;
import com.example.h_item.model.req.FundReq;
import com.example.h_item.model.req.IdRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Date 2024/4/27
 */
@Service
public class FundService {

    @Resource
    private FundMapper fundMapper;

    @Resource
    private ProjectService projectService;

    @Resource
    private UserLoginCache userLoginCache;

    @Resource
    private UserService userService;

    /**
     * 申请经费
     */
    public void apply(FundApplyReq req) {
        ProjectPO projectPO = projectService.queryById(req.getProId());
        Assert.notNull(projectPO, "找不到项目信息");

        Long userId = userLoginCache.getUserIdByToken(LoginUtil.get());
        UserPO userPO = userService.queryById(userId);
        Assert.notNull(userPO, "找不到用户信息");

        FundPO fundPO = new FundPO();
        fundPO.setProId(projectPO.getId());
        fundPO.setProName(projectPO.getProName());
        fundPO.setFund(req.getFund());
        fundPO.setUserId(userId);
        fundPO.setAccount(req.getAccount());
        fundPO.setRemark(req.getRemark());
        fundPO.setStatus(FundStatusEnum.WAIT_AUDIT.name());
        fundMapper.insert(fundPO);
    }

    /**
     * 列表查询
     */
    public Pager<FundPO> pageQuery(FundReq req) {
        Long userId = userLoginCache.getUserIdByToken(LoginUtil.get());
        UserPO userPO = userService.queryById(userId);
        Assert.notNull(userPO, "找不到用户信息");
        if (userPO.getRoleId() != 3) {
            req.setUserId(userId);
        }
        List<FundPO> fundPOS = fundMapper.pageList(req);
        return new Pager<>(new Pager.PageData(req.getPage(), fundMapper.pageListCount(req)), fundPOS);
    }

    public FundPO queryById(Long id) {
        return fundMapper.queryById(id);
    }

    public void cancel(IdRequest req) {
        FundPO fundPO = fundMapper.queryById(req.getId());
        Assert.notNull(fundPO, "找不到对应的经费信息");
        Assert.isTrue(StrUtil.equals(fundPO.getStatus(), FundStatusEnum.WAIT_AUDIT.name()),
                "待审核的经费申请才能取消");
        fundPO.setStatus(FundStatusEnum.CANCEL.name());
        fundMapper.update(fundPO);
    }

    public void audit(FundAuditReq req) {
        FundPO fundPO = fundMapper.queryById(req.getFundId());
        Assert.notNull(fundPO, "找不到对应的经费信息");
        Assert.isTrue(StrUtil.equals(fundPO.getStatus(), FundStatusEnum.WAIT_AUDIT.name()),
                "待审核的经费申请才能审核");
        fundPO.setStatus(req.getAcceptFlag() ? FundStatusEnum.ACCEPT.name() : FundStatusEnum.REFUSE.name());
        fundMapper.update(fundPO);
    }
}
