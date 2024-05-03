package com.example.h_item.controller;


import cn.hutool.core.lang.Assert;
import com.example.h_item.common.Pager;
import com.example.h_item.common.Result;
import com.example.h_item.model.po.FundPO;
import com.example.h_item.model.req.FundApplyReq;
import com.example.h_item.model.req.FundAuditReq;
import com.example.h_item.model.req.FundReq;
import com.example.h_item.model.req.IdRequest;
import com.example.h_item.service.FundService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/fund")
public class FundController {

    @Resource
    private FundService fundService;

    @PostMapping("/apply")
    public Result<Void> apply(@RequestBody FundApplyReq req) {
        fundService.apply(req);
        return Result.success();
    }

    @PostMapping("/cancel")
    public Result<Void> cancel(@RequestBody @Valid IdRequest req) {
        fundService.cancel(req);
        return Result.success();
    }

    @PostMapping("/pageQuery")
    public Result<Pager<FundPO>> pageQuery(@RequestBody @Valid FundReq req) {
        return Result.success(fundService.pageQuery(req));
    }

    @PostMapping("/audit")
    public Result<Void> audit(@RequestBody @Valid FundAuditReq req) {
        fundService.audit(req);
        return Result.success();
    }
}
