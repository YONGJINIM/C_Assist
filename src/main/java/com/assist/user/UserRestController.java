package com.assist.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assist.user.bo.UserBO;

@RequestMapping("/user")
@RestController
public class UserRestController {

	 @Autowired
	 private UserBO userBO;

	 @GetMapping("/is-duplicate-id")
	 public Map<String, Object> isDuplicateId(@RequestParam("loginId") String loginId) {
		 boolean isDuplicate = userBO.isDuplicateUserId(loginId);

		 Map<String, Object> result = new HashMap<>();
		 result.put("code", 200); // 성공 코드
		 result.put("is_duplicate_id", isDuplicate); // 중복 여부
		 return result;
	 }
}
