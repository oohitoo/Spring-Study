package com.mySpring.springBoard2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Board2DTO {
	
	private int id;
	private String name;
	private String title;
	private String content;
	private String regdate;
	private int hit;
}
