package com.study.servlet_study.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
	
	private String username;
	private String password;
	private String name;
	private String email;
	

}
