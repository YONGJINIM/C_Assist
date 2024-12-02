package com.assist.user.bo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assist.common.Encrypter;
import com.assist.user.mapper.UserMapper;

@Service
public class UserBO {

	@Autowired
	private UserMapper userMapper;
	
	 @Autowired
	 private Encrypter encrypter;
	
	
	// 아이디 중복체크 
	public boolean isDuplicateUserId(String loginId) {
        return userMapper.selectUserByUserId(loginId);
    }
	
    // 회원가입
    public int addUser(
        String loginId,
        String password,
        double remainingLeave,
        String name,
        String position,
        String division,
        String startDate,
        String phoneNumber,
        String email
    ) {
    	
    	// 패스워드 암호화
        Map<String, String> encryptedPassword = encrypter.generateHash(password);
        String encryptedPasswordStr = encryptedPassword.get("encryptedString"); // 암호화된 비밀번호
        String salt = encryptedPassword.get("salt"); // 생성된 salt
    	
    	
        // Mapper 호출
        return userMapper.insertUser(
        		loginId, 
        		encryptedPasswordStr, 
        		salt, 
        		remainingLeave, 
        		name, 
        		position, 
        		division, 
        		startDate, 
        		phoneNumber, 
        		email
        	);
    }
}
