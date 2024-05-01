package com.example.h_item.mapper;

import com.example.h_item.model.po.FundPO;
import com.example.h_item.model.req.FundReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 经费表
 * @author BEJSON
 * @date 2024-04-27
 */
@Mapper
public interface AcademyMapper {

    /**
    * 查询 分页查询
    **/
    List<FundPO> queryList();


}