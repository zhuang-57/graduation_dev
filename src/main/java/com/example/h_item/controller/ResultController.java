package com.example.h_item.controller;


import com.example.h_item.common.Pager;
import com.example.h_item.common.Result;
import com.example.h_item.model.dto.ResultAnalysisDTO;
import com.example.h_item.model.po.FundPO;
import com.example.h_item.model.po.ResultPO;
import com.example.h_item.model.req.*;
import com.example.h_item.service.FundService;
import com.example.h_item.service.ResultService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/result")
public class ResultController {

    @Resource
    private ResultService resultService;

    @PostMapping("/apply")
    public Result<Void> apply(@RequestBody ResultApplyReq req) {
        resultService.apply(req);
        return Result.success();
    }

    @PostMapping("/cancel")
    public Result<Void> cancel(@RequestBody @Valid IdRequest req) {
        resultService.cancel(req);
        return Result.success();
    }

    @PostMapping("/pageQuery")
    public Result<Pager<ResultPO>> pageQuery(@RequestBody @Valid ResultReq req) {
        return Result.success(resultService.queryPager(req));
    }

    @PostMapping("/audit")
    public Result<Void> audit(@RequestBody @Valid ResultAuditReq req) {
        resultService.audit(req);
        return Result.success();
    }

    @PostMapping("/analysis")
    public Result<ResultAnalysisDTO> analysis() {
        return Result.success(resultService.resultAnalysis());
    }
}
