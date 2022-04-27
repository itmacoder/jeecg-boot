package org.jeecg.modules;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.platform.dataexchange.entity.FxJobHis;
import org.jeecg.modules.platform.dataexchange.service.IFxJobHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
public abstract class BaseReqOperate implements ReqOperate {
    public static Map<String, BaseReqOperate> reqOperateMap = new ConcurrentHashMap<String, BaseReqOperate>();
    @Autowired
    private IFxJobHisService fxJobHisService;
    /**
     * 渠道
     *
     * @return
     */
    public abstract String getChannel();

    public abstract ResponseEntity<JSONObject>  doResuest(String url, HttpMethod method, HttpHeaders headers, JSONObject variables, Object params);

    @Override
    public ResponseEntity<JSONObject> process(String url, HttpMethod method, HttpHeaders headers, JSONObject variables, Object params) {
       Long startTime = System.currentTimeMillis();
        // do GET/POST request
        ResponseEntity<JSONObject> data = doResuest(url,method,headers,variables,params);
        // log in DB
        afterProcess(data,startTime);
        return data;
    }


    @PostConstruct
    public void init() {
        reqOperateMap.put(getChannel(), this);
    }

    public  void afterProcess(ResponseEntity<JSONObject> data,Long startTime){
        FxJobHis fxJobHis = new FxJobHis();
        fxJobHis.setJobName(this.getChannel());
        fxJobHis.setReturnData(data.getBody().toJSONString());
        fxJobHis.setStatus(data.getStatusCode().value());
        fxJobHis.setUpdateTime(new Date());
        fxJobHis.setExecutionTime(DateUtils.getDate(startTime));
        fxJobHis.setCostTime((int) (System.currentTimeMillis()-startTime));
        fxJobHisService.save(fxJobHis);
        log.info("我要存DB");
    }


}
