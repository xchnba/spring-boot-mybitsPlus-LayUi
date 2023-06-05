package com.example.project.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.project.entity.SharesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SharesEntityMapper extends BaseMapper<SharesEntity> {

    List<SharesEntity> getSharesByDateSearch();

}