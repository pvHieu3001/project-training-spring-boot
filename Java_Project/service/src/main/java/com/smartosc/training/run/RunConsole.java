package com.smartosc.training.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 31/07/2020 - 4:20 PM
 * @created_by Hieupv
 * @since 31/07/2020
 */
@Component
public class RunConsole implements CommandLineRunner {
    @Autowired
    private RedisTemplate template;


    @Override
    public void run(String... args) throws Exception {
        listExample();
    }

    public void valueExample(){
        // Set giá trị của key "loda" là "hello redis"
        template.opsForValue().set("loda","hello world");

        // In ra màn hình Giá trị của key "loda" trong Redis
        System.out.println("Value of key loda: "+template.opsForValue().get("loda"));
    }

    public void listExample(){
        // Tạo ra một list gồm 2 phần tử
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("redis");

        // Set gia trị có key loda_list
        template.opsForList().rightPushAll("loda_list", list);
//        template.opsForList().rightPushAll("loda_list", "hello", "world");
        Object a = template.opsForList();
        System.out.println("Size of key loda: "+template.opsForList().index("loda_list", 1));
    }
}
