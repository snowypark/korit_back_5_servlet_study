package com.study.servlet_study.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Author {
	
	private int authorId;
	private String authorName;
	
	


}
