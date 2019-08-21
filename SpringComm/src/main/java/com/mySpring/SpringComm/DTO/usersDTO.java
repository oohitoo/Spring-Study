package com.mySpring.SpringComm.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class usersDTO {	
	private int idx;
	private String userid;
	private String userpwd;
	private String username;
	private String useremail;
}
