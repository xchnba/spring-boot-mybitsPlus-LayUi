package com.example.demo.mall.mapper;

import com.example.demo.mall.domain.Student;
import com.example.demo.mall.domain.User;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from users")
    List<User> findUserList();
//    @Select("select * from users")
//    List<User> findUserListByPageObj(Page<User> st);
}
