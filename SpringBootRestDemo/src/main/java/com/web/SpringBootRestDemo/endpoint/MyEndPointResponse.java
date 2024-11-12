package com.web.SpringBootRestDemo.endpoint;

import com.web.SpringBootRestDemo.utility.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyEndPointResponse{
	private int id;
	private String message;
	private Status status;
}