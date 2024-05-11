package com.example.h_item.mapper;

import com.example.h_item.model.po.ResultPO;
import com.example.h_item.model.req.ResultReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description 成果表
 * @date 2024-05-04
 */
@Mapper
@Repository
public interface ResultMapper {

    int insert(ResultPO resultPO);

    int delete(Long id);

    int update(ResultPO resultPO);

    ResultPO queryById(Long id);

    List<ResultPO> queryPager(ResultReq req);

    int count(ResultReq req);

    List<ResultPO> queryAll(ResultReq req);
}