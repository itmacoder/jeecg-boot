package org.jeecg.modules.system.test;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.JeecgSystemApplication;
import org.jeecg.common.util.RestUtil;
import org.jeecg.modules.ReqService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = JeecgSystemApplication.class)
//@SuppressWarnings({"FieldCanBeLocal", "SpringJavaAutowiredMembersInspection"})
public class HttpTest {

    /**
     * 测试地址：实际使用时替换成你自己的地址
     */
    private final String BASE_URL = "https://console-api.apipost.cn";

    // 请实际使用时替换成你自己的用户名和密码
    private final String USERNAME = "admin";
    private final String PASSWORD = "123456";
    @Resource
    ReqService reqService;

    @Test
    public void process() {
        // 用户Token
        String token = this.getToken();
        // 请求地址
        String url = BASE_URL + "/api/demo/news_details";
        // 请求 Header （用于传递Token）
        HttpHeaders headers = this.getHeaders(token);
        // 请求方式是 GET 代表获取数据
        HttpMethod method = HttpMethod.GET;

        System.out.println("请求地址：" + url);
        System.out.println("请求方式：" + method);
        System.out.println("请求Token：" + token);

        JSONObject variables = new JSONObject();
        variables.put("status",1);
        variables.put("id",20);
        // 利用 RestUtil 请求该url

        reqService.process(url, method, headers, variables, null);
    }
    /**
     * 测试用例：查询记录
     */
    @Test
    public void testQuery() {
        // 用户Token
        String token = this.getToken();
        // 请求地址
        String url = BASE_URL + "/api/demo/news_details";
        // 请求 Header （用于传递Token）
        HttpHeaders headers = this.getHeaders(token);
        // 请求方式是 GET 代表获取数据
        HttpMethod method = HttpMethod.GET;

        System.out.println("请求地址：" + url);
        System.out.println("请求方式：" + method);
        System.out.println("请求Token：" + token);

        JSONObject variables = new JSONObject();
        variables.put("status",1);
        variables.put("id",20);
        // 利用 RestUtil 请求该url

        ResponseEntity<JSONObject> result = RestUtil.request(url, method, headers, variables, null, JSONObject.class);
        if (result != null && result.getBody() != null) {
            System.out.println(result.getStatusCode());
            System.out.println("返回结果：" + result.getBody().toJSONString());
        } else {
            System.out.println("查询失败");
        }
    }

    private String getToken() {
        String token = "2ed7d7721fef5b9d7702182e5ffa0e48";
        // JwtUtil.sign(USERNAME, PASSWORD);
        return token;
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
