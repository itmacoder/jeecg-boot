package org.jeecg.modules;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface ReqService {
     ResponseEntity<JSONObject> process(String url, HttpMethod method, HttpHeaders headers, JSONObject variables, Object params);
}
