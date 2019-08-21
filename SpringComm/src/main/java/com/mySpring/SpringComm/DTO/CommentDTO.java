package com.mySpring.SpringComm.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
	
	private String username;
	private String usercomment;
	private int sercret;
	private String datetime;
	
}
