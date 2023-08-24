package com.example.longtimeapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author christinehsieh on 2023/8/13
 */
@RestController
@RequestMapping("/")
public class LongTimeTaskController {


    @GetMapping("/longtime")
    public String getSum() throws InterruptedException {

        Thread.sleep(1000);
        return "longtime : " + new Date();
    }
}
