package org.jeecg.modules;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.RestUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
* @Description:
*  发送请求
 * 记录发送信息
* @Param:
* @Author: mawh
* @Date: 2022/4/17
*/
@Service
@Slf4j
public class ReqServiceImpl  implements ReqService {


    public ResponseEntity<JSONObject>  process(String url, HttpMethod method, HttpHeaders headers, JSONObject variables, Object params) {
//        ResponseEntity<JSONObject> result = RestUtil.request(url, method, headers, variables, null, JSONObject.class);
      log.info("模版业务操作1");
        ResponseEntity<JSONObject> result = BaseReqOperate.reqOperateMap.get("newsChannel")
                .process(url, method, headers, variables, null);
        return result;
    }



}
