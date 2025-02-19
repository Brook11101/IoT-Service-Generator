package com.applet.mpinterface.service;

import com.applet.mpinterface.domain.pojo.Rundetails;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ISpecificGenerator extends IService<Rundetails> {
    public void generateAllDetailgetterCode(String id,Integer actionTime,String path,String appletName);
}
