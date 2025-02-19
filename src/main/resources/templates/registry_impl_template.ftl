
package com.${appletName}.mpinterface.service.impl;


import com.${appletName}.mpinterface.service.ISpecificService;
import iotruleservice.I${appletName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import spring.annotation.RemoteServiceReference;
import lombok.extern.slf4j.Slf4j;


@RemoteServiceReference //对于带有这种注解的，则直接进行服务的注册与发布
@Slf4j
@Service
public class ${appletName}ServiceImpl implements I${appletName}Service {
@Autowired
private ISpecificService specificService;
// 选择具体的select值
<#list fields as field>
    public String ${field.name}(String id,Integer actionTime)
    {
      return specificService.select${field.name?cap_first}(id, actionTime);
    }
</#list>
}
