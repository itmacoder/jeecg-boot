package org.jeecg.modules;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.jeecg.common.util.RestUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NewsReqOperate  extends  BaseReqOperate{

    @Override
    public T completeReqOperate() {
        log.info("插入数据库");
        return null;
    }


    /**
     * 渠道
     *
     * @return
     */
    @Override
    public String getChannel() {
        return "newsChannel";
    }

    @Override
    public ResponseEntity<JSONObject> doResuest(String url, HttpMethod method, HttpHeaders headers, JSONObject variables, Object params) {
       log.info("执行请求2");
        ResponseEntity<JSONObject> result = RestUtil.request(url, method, headers, variables, null, JSONObject.class);
        return result;
    }


}
