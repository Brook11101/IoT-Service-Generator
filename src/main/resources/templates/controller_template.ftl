package com.${appletName}.mpinterface.controller;

import iotruleservice.I${appletName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specific")
public class SpecificController {
@Autowired
private I${appletName}Service ${appletName}Service;
//模板生成下面的mapping就行
<#list fields as field>
@GetMapping("/${field.name}")
public String ${field.name?cap_first}(
@RequestParam String id,
@RequestParam Integer time) {
// 处理参数逻辑
String a = ${appletName}Service.${field.name}(id, time);
// 返回视图名称或重定向URL
return a;
}

</#list>
}
