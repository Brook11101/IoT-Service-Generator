package com.applet.mpinterface.controller;

import com.applet.mpinterface.domain.pojo.Appletinfo;
import com.applet.mpinterface.domain.pojo.Stage;
import com.applet.mpinterface.service.IAppletinfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/appletinfo")
public class AppletinfoController {

    @Autowired
    private IAppletinfoService appletinfoService;

    @GetMapping("/appletname")
    public String appletName(
            @RequestParam String id) {

        QueryWrapper<Appletinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return appletinfoService.list(queryWrapper).get(0).getAppletName();

    }

    @GetMapping("/actiondelay")
    public Integer actionDelay(
            @RequestParam String id) {
        QueryWrapper<Appletinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return appletinfoService.list(queryWrapper).get(0).getActionsDelay();

    }

    @GetMapping("/speed")
    public String Speed(
            @RequestParam String id) {
        QueryWrapper<Appletinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return appletinfoService.list(queryWrapper).get(0).getSpeed();
    }

    @GetMapping("/channelid")
    public String channelID(
            @RequestParam String id,
            @RequestParam Stage stage) {
        QueryWrapper<Appletinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("Stage", stage);
        return appletinfoService.getOne(queryWrapper).getChannelId();

    }

    @GetMapping("/channelname")
    public String channelName(
            @RequestParam String id,
            @RequestParam Stage stage) {
        QueryWrapper<Appletinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("Stage", stage);
        return appletinfoService.getOne(queryWrapper).getChannelName();
    }

    @GetMapping("/modulename")
    public String modelName(
            @RequestParam String id,
            @RequestParam Stage stage) {
        QueryWrapper<Appletinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("Stage", stage);
        return appletinfoService.getOne(queryWrapper).getModuleName();
    }

    @GetMapping("/permissionname")
    public String perName(
            @RequestParam String id,
            @RequestParam Stage stage) {
        QueryWrapper<Appletinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("Stage", stage);
        return appletinfoService.getOne(queryWrapper).getPermissionName();
    }

    @GetMapping("/description")
    public String des(
            @RequestParam String id,
            @RequestParam Stage stage) {
        QueryWrapper<Appletinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("Stage", stage);
        return appletinfoService.getOne(queryWrapper).getDescription();
    }
}
