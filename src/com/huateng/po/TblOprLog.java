package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblOprLog implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String oprId;
	private java.lang.String oprType;
	private java.lang.String oprDate;


}