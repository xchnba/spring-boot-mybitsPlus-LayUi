package com.example.project.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.project.entity.UserUnknow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserUnknowMapper extends BaseMapper<UserUnknow> {

    Integer getUserUnknowCount(@Param("openId") String openId);

    List<UserUnknow> getUnKnowDanciByParam(@Param("dan") String dan,@Param("chang") String chang);
}