package com.mySpring.tran.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranDTO {	

	private String counsumerId;
	private int amount;
	
}
