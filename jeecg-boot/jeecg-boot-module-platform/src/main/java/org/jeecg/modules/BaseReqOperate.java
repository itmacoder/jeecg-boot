package org.jeecg.modules;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
public abstract class BaseReqOperate implements ReqOperate {
    public static Map<String, BaseReqOperate> reqOperateMap = new ConcurrentHashMap<String, BaseReqOperate>();

    /**
     * 渠道
     *
     * @return
     */
    public abstract String getChannel();

    public abstract ResponseEntity<JSONObject>  doResuest(String url, HttpMethod method, HttpHeaders headers, JSONObject variables, Object params);

    @Override
    public ResponseEntity<JSONObject> process(String url, HttpMethod method, HttpHeaders headers, JSONObject variables, Object params) {
        // do GET/POST request
        ResponseEntity<JSONObject> data = doResuest(url,method,headers,variables,params);
        // log in DB
        afterProcess(data);
        return data;
    }


    @PostConstruct
    public void init() {
        reqOperateMap.put(getChannel(), this);
    }

    public  void afterProcess(ResponseEntity<JSONObject> data){
        log.info("我要存DB");
    }


}
