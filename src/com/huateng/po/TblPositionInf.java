package com.huateng.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblPositionInf implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// primary key
	private java.lang.String id;

	// fields
	
	private String positionName ;
	private String positionDes ;
	private String sortNo; 
	private String createTime ;
	private String updateTime ;
	private String createOpr ;
	private String updateOpr ;
	
	
	

}