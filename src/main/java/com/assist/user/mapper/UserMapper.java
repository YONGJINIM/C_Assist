package com.assist.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.assist.user.domain.User;

@Mapper
public interface UserMapper {

	// test
	public List<Map<String, Object>> selectUserList();

	boolean selectUserByUserId(String loginId); 
}
	
