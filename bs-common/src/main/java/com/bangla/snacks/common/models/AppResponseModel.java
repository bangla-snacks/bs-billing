package com.bangla.snacks.customer.json.models;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppResponseModel<T> implements Serializable {
	private static final long serialVersionUID = 3891019634004996128L;
	
	private T body;
	private RequestMetaData metaData;
	private String finalStatus;
	private int recordReturned;

}
