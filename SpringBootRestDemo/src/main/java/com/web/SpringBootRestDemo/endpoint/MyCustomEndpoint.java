package com.web.SpringBootRestDemo.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import com.web.SpringBootRestDemo.utility.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Endpoint(id = "custom", enableByDefault = true)
public class MyCustomEndpoint {

	@ReadOperation
	public MyEndPointResponse getMessage() {
		return new MyEndPointResponse(1, "some message", Status.SUCCESS);
	}
//	@ReadOperation
//	public MyEndPointResponse getMessage1() {
//		return new MyEndPointResponse(1, "some message", Status.SUCCESS);
//	}
	
	@WriteOperation
	public MyEndPointResponse addResponse(String message) {
		MyEndPointResponse resp = new MyEndPointResponse();
		resp.setId(10);
		resp.setMessage(message);
		resp.setStatus(Status.FAILURE);
		return resp;
	}
	@DeleteOperation
	public String deleteResponse(@Selector int id) {
		return "deleted "+id;
	}
}


