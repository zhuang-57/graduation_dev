package com.example.h_item.controller;

import com.example.h_item.common.Result;
import com.example.h_item.model.po.FundPO;
import com.example.h_item.service.AcademyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/academy")
public class AcademyController {

    @Resource
    private AcademyService academyService;

    @PostMapping("/queryList")
    public Result<List<FundPO>> queryList() {
        return Result.success(academyService.queryList());
    }
}
