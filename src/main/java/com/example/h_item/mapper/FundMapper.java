package com.example.h_item.mapper;

import com.example.h_item.model.po.FundPO;
import com.example.h_item.model.req.FundReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 经费表
 * @date 2024-04-27
 */
@Mapper
public interface FundMapper {

    /**
    * 新增
    **/
    int insert(FundPO fund);

    /**
    * 刪除
    **/
    int delete(Long id);

    /**
    * 更新
    **/
    int update(FundPO fund);

    /**
    * 查询 根据主键 id 查询
    **/
    FundPO queryById(Long id);

    /**
    * 查询 分页查询
    **/
    List<FundPO> pageList(FundReq req);

    /**
    * 查询 分页查询 count
    **/
    int pageListCount(FundReq req);

}