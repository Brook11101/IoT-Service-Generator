package com.applet.mpinterface.service.impl;

import com.applet.mpinterface.domain.pojo.Rundetails;
import com.applet.mpinterface.mapper.RundetailsMapper;
import com.applet.mpinterface.service.IRundetailsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RundetailsServiceImpl extends ServiceImpl<RundetailsMapper, Rundetails> implements IRundetailsService {
    @Autowired
    private RundetailsMapper rundetailsMapper;

    @Override
    public Rundetails getLatestRecordById(String id) {
        // 构建查询条件
        QueryWrapper<Rundetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).orderByDesc("action_time").last("LIMIT 1");  // 仅获取一条记录

        // 执行查询
        return rundetailsMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Rundetails> getLatestRecordsById(String id) {
        // 构建查询条件
        QueryWrapper<Rundetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).orderByDesc("action_time");  // 仅获取一条记录
        // 执行查询
        return rundetailsMapper.selectList(queryWrapper);
    }

}
