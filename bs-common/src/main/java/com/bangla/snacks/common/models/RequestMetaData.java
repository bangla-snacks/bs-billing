package com.bangla.snacks.common.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RequestMetaData {
	private String requestedBy;
	private Date requestTimestamp;
	private String userRight;
}
