package com.assist.calender;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalenderController {

	
	// 영업 캘린더 화면  
	@GetMapping("/business-calender-view")
	public String businessCalenderView() {
		return "business/calender";
	}
}
