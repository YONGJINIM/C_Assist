package com.assist.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	
	// 로그인 화면 
	@GetMapping("/sign-in-view")
	public String signInView() {
		return "user/signIn";
	}
	
	// 회원가입 화면 
	@GetMapping("/sign-up-view")
	public String signUpView() {
		return "user/signUp";  
	}
	
	// 아이디 비밀번호 찾기 
	@GetMapping("/find-idpw-view")
	public String findIdPwView() {
		return "user/findIdPw";
	}
	
	// 아이디 찾기 결과 
	@GetMapping("/find-idresult-view")
	public String findIdResultView() {
		return "user/findIdResult";
	}
	
	// 비밀번호 찾기 결과 
	@GetMapping("/find-pwresult-view")
	public String findPwResultView() {
		return "user/findPwResult";
	}		
}
