package com.assist.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assist.user.mapper.UserMapper;

@Service
public class UserBO {

	@Autowired
	private UserMapper userMapper;
	
	public boolean isDuplicateUserId(String loginId) {
        return userMapper.selectUserByUserId(loginId);
    }
}
