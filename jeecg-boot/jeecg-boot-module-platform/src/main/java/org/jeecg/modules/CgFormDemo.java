package org.jeecg.modules;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component("cgFormDemo")
public class CgFormDemo implements CgformEnhanceJavaInter {
    @Override
    public void execute(String table, JSONObject jsonObject) throws BusinessException {
        System.out.println("我是自定义java增加测试bean");
        System.out.println("当前tableName="+table);
        System.out.println("当前JSON数据="+jsonObject.toString());
    }
}
