package com.ten.lifecat.ssm.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.ten.lifecat.ssm.entity.UserIcon;

public interface UserIconDao {

    int insert(@Param("pojo") UserIcon pojo);

    int insertList(@Param("pojos") List<UserIcon> pojo);

    List<UserIcon> select(@Param("pojo") UserIcon pojo);

    int update(@Param("pojo") UserIcon pojo);

    int delete(@Param("pojo") UserIcon pojo);

}
