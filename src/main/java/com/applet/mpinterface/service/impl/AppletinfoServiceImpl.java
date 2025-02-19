package com.applet.mpinterface.service.impl;

import com.applet.mpinterface.domain.pojo.Appletinfo;
import com.applet.mpinterface.mapper.AppletinfoMapper;
import com.applet.mpinterface.service.IAppletinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class AppletinfoServiceImpl extends ServiceImpl<AppletinfoMapper, Appletinfo> implements IAppletinfoService {

}
