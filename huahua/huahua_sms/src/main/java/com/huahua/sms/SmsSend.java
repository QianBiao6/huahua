package com.huahua.sms;

import com.huahua.sms.send.SmsSendCode;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsSend {

    /**
     * 发送短信
     * @param map
     */
    @RabbitHandler
    public void sendSms(Map<String,String> map){
        System.out.println("手机号："+map.get("mobile"));
        System.out.println("验证码："+map.get("code"));
        //System.out.println("验证码发送成功");
        SmsSendCode.sendCode(map.get("code"));
    }
}
