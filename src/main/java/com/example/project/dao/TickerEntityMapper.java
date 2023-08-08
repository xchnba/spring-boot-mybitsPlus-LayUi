package com.example.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.project.entity.TickerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TickerEntityMapper extends BaseMapper<TickerEntity> {

    List<TickerEntity> selectByDate(@Param("start")String start,@Param("end") String end);

}