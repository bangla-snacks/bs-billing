package com.bangla.snacks.customer.json.models;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestMetaData {
	private String requestedBy;
	private Date requestTimestamp;
	private String userRight;
}
