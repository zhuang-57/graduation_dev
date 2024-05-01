package com.example.h_item.service;

import com.example.h_item.mapper.AcademyMapper;
import com.example.h_item.model.po.FundPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Date 2024/4/27
 * @Created by wangshuai
 */
@Service
public class AcademyService {

    @Resource
    private AcademyMapper academyMapper;


    public List<FundPO> queryList() {
        return academyMapper.queryList();
    }
}
