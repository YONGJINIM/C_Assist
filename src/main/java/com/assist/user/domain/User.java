package com.assist.user.domain;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class User {
	private int id;
	private String loginId;
	private String password;
	private double remainingLeave;
	private String name;
	private String position;
	private String division;
	private Date startDate;
	private String phoneNumber;
	private String email;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	
}
