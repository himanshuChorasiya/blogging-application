package com.hblog.api.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {


//	public ApiResponse(String string, boolean b) {
//		// TODO Auto-generated constructor stub
//		System.out.println("User deleted");
//	}
	private String Message;
	private boolean success;
}
