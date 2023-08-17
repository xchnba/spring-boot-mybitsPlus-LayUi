package com.example.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.project.entity.Vocabulary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface VocabularyMapper extends BaseMapper<Vocabulary> {

    List<Vocabulary> selectByChangDu();

}