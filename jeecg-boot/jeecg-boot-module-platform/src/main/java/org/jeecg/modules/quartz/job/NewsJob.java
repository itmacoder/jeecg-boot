package org.jeecg.modules.quartz.job;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.ReqOperate;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;

@Slf4j
public class NewsJob implements Job {
    /**
     * 若参数变量名修改 QuartzJobController中也需对应修改
     */
    private String parameter;

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Resource
    private ReqOperate reqService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(" Job Execution key："+jobExecutionContext.getJobDetail().getKey());
        log.info( String.format("welcome %s! Jeecg-Boot 带参数定时任务 SampleParamJob !   时间:" + DateUtils.now(), this.parameter));
        log.info("获取表信息 https://console-api.apipost.cn/api/demo/news_details?id=20&status=1");
        String url = "https://console-api.apipost.cn/api/demo/news_details";
        String token = "2ed7d7721fef5b9d7702182e5ffa0e48";
        HttpHeaders headers = this.getHeaders(token);
        JSONObject variables = new JSONObject();
        variables.put("status",1);
        variables.put("id",20);
        ResponseEntity<JSONObject> data = reqService.process(url, HttpMethod.GET, headers, variables, null);
        System.out.println(data.getBody());

    }

    private HttpHeaders getHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        String mediaType = MediaType.APPLICATION_JSON_UTF8_VALUE;
        headers.setContentType(MediaType.parseMediaType(mediaType));
        headers.set("Accept", mediaType);
        headers.set("token", token);
        return headers;
    }
}
