package com.youmeek.ssm;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by songyongchao on 2017/5/11.
 */
public class MainConsumer {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:applicationContext-active.xml");
        context.start();
    }
}
