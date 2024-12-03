package com.assist.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assist.user.bo.UserBO;
import com.assist.user.domain.User;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {

	 @Autowired
	 private UserBO userBO;

	 // 아이디 중복 확인
	 @GetMapping("/is-duplicate-id")
	 public Map<String, Object> isDuplicateId(@RequestParam("loginId") String loginId) {
		 boolean isDuplicate = userBO.isDuplicateUserId(loginId);

		 Map<String, Object> result = new HashMap<>();
		 result.put("code", 200); // 성공 코드
		 result.put("is_duplicate_id", isDuplicate); // 중복 여부
		 return result;
	 }
	 
	 // 회원 가입 
	 @PostMapping("/sign-up")
	 public Map<String, Object> signUp(
	     @RequestParam("loginId") String loginId,
	     @RequestParam("password") String password,
	     @RequestParam(value = "remainingLeave", required = false, defaultValue = "10.0") Double remainingLeave,
	     @RequestParam("name") String name,
	     @RequestParam("position") String position,
	     @RequestParam("division") String division,
	     @RequestParam("startDate") String startDate,
	     @RequestParam("phoneNumber") String phoneNumber,
	     @RequestParam("email") String email
	 ) {
	    
		 Map<String, Object> result = new HashMap<>();
	     try {
	         //유효성 검사 (예: remainingLeave가 음수인지 확인)
	         if (remainingLeave < 0) {
	             result.put("code", 400);
	             result.put("error_message", "남은 연차는 음수일 수 없습니다.");
	             return result;
	         }

	         // 사용자 추가 로직
	         int rowCount = userBO.addUser(
	             loginId,
	             password,
	             remainingLeave,
	             name,
	             position,
	             division,
	             startDate,
	             phoneNumber,
	             email
	         );

	         if (rowCount == 1) {
	             // 성공 응답
	             result.put("code", 200);
	             result.put("message", "회원가입이 완료되었습니다.");
	         } else {
	             // 로직 실패 응답
	             result.put("code", 500);
	             result.put("error_message", "회원가입 중 문제가 발생했습니다. 다시 시도해주세요.");
	         }
		     } catch (Exception e) {
		         // 예외 처리 응답
		         result.put("code", 400);
		         result.put("error_message", "잘못된 요청입니다. 확인 후 다시 시도해주세요.");
		         e.printStackTrace();
		     }
		     return result;
		 }
	  	// 로그인
	    @PostMapping("/sign-in")
	    public Map<String, Object> signIn(
	    		@RequestParam("loginId") String loginId,
	    		@RequestParam("password") String password,
	            HttpSession session) {

	        Map<String, Object> result = new HashMap<>();

	        // BO를 통해 로그인 처리
	        User user = userBO.login(loginId, password);

	        if (user != null) {
	            // 세션 저장
	            session.setAttribute("userId", user.getId());
	            session.setAttribute("userLoginId", user.getLoginId());
	            session.setAttribute("userName", user.getName());

	            result.put("success", true); // 성공
	            result.put("message", "로그인 성공!");
	        } else {
	            result.put("success", false); // 실패
	            result.put("message", "아이디 또는 비밀번호를 확인해주세요.");
	        }
	        return result;
	    }
	}