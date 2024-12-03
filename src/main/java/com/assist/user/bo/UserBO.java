package com.assist.user.bo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assist.common.Encrypter;
import com.assist.user.domain.User;
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
        		email);
    }
    // 로그인 처리
    public User login(String loginId, String password) {
        // 1. 사용자 정보 조회
        User user = userMapper.selectUserByLoginId(loginId);

        if (user == null) {
            // 사용자가 존재하지 않음
            return null;
        }

        // 2. 사용자 정보에서 salt와 암호화된 비밀번호 가져오기
        String storedSalt = user.getSalt();
        String storedPassword = user.getPassword();

        // 3. 입력된 비밀번호를 사용자의 salt로 해싱
        boolean isPasswordMatch = encrypter.matchWithEncryptedString(password, storedPassword, storedSalt);

        if (!isPasswordMatch) {
            // 비밀번호가 일치하지 않음
            return null;
        }

        // 4. 로그인 성공 - 사용자 객체 반환
        return user;
    }
}