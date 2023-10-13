package com.example.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.project.entity.DcMemoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DcMemoryEntityMapper extends BaseMapper<DcMemoryEntity> {

    DcMemoryEntity getDcMemory(@Param("danci") String danci,@Param("openId")  String openId);
}