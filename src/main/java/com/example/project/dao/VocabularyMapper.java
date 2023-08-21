package com.example.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.project.entity.Vocabulary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface VocabularyMapper extends BaseMapper<Vocabulary> {

    List<Vocabulary> selectByChangDu();

    Vocabulary getByDanci(@Param("danci") String danci);

    List<Vocabulary> getByDanciByParam(@Param("test")Integer test,@Param("uses") Integer uses,@Param("star") Integer star,@Param("sort") Integer sort,@Param("dan") String dan);
}