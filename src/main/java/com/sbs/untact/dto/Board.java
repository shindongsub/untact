package com.sbs.untact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {

	private int id;
	private String regDate;
	private String updateDate;
	private String code;
	private String name;
}
