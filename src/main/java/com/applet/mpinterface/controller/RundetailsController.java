package com.applet.mpinterface.controller;

import com.applet.mpinterface.domain.pojo.Rundetails;
import com.applet.mpinterface.service.IRundetailsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rundetails")
public class RundetailsController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @Autowired
    private IRundetailsService rundetailsService;

    @GetMapping("/actiontype")
    public String actionType(
            @RequestParam String id, @RequestParam Integer time) {
        QueryWrapper<Rundetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("action_time", time);
        return rundetailsService.getOne(queryWrapper).getActionType();
    }

    @GetMapping("/newesttype")
    public String actionNewestType(
            @RequestParam String id) {
        return rundetailsService.getLatestRecordById(id).getActionType();

    }

    @GetMapping("/newesttime")
    public Integer actionNewestTime(
            @RequestParam String id) {
        //返回最新的时间
        return rundetailsService.getLatestRecordById(id).getActionTime();

    }
}
