package com.assist.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.assist.user.domain.User;

@Mapper
public interface UserMapper {

	// test
	public List<Map<String, Object>> selectUserList();

	// loginId 중복확인 
	boolean selectUserByUserId(String loginId); 
	
    // 회원가입
    public int insertUser(
        @Param("loginId") String loginId,
        @Param("password") String password,
        @Param("salt") String salt, // salt 추가
        @Param("remainingLeave") double remainingLeave,
        @Param("name") String name,
        @Param("position") String position,
        @Param("division") String division,
        @Param("startDate") String startDate,
        @Param("phoneNumber") String phoneNumber,
        @Param("email") String email
    );
}
	
