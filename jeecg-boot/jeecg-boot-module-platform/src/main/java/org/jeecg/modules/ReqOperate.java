package org.jeecg.modules;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public  interface ReqOperate {


    ResponseEntity<JSONObject> process(String url, HttpMethod method, HttpHeaders headers, JSONObject variables, Object params);


    T completeReqOperate();
}
