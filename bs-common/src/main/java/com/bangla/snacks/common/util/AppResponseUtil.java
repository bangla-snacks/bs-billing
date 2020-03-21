package com.bangla.snacks.common.util;

import com.bangla.snacks.common.models.AppResponseModel;
import com.bangla.snacks.common.models.RequestMetaData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public class AppResponseUtil {
    @SuppressWarnings("rawtypes")
    public static ResponseEntity<Object> returnResponse(Object body, HttpStatus status) {
        AppResponseModel<Object> response = AppResponseModel.builder()
                .body(body)
                .metaData(RequestMetaData.builder()
                        .requestedBy("APPLICATION")
                        .requestTimestamp(new Date())
                        .build())
                .finalStatus(status.isError() ? "FAILURE" : "Success")
                .build();
        if(body instanceof List) {
            response.setRecordReturned(((List)body).size());
        } else {
            response.setRecordReturned(status.isError() ? 0 : 1);
        }
        return new ResponseEntity<>(response, defaultHeaders(), status);
    }

    private static HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
        headers.add(HttpHeaders.CACHE_CONTROL, "No-Cache");
        return headers;
    }
}
