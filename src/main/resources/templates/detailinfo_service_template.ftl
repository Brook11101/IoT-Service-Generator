<#escape x as x?html>
package com.${appletName}.mpinterface.service;
    import com.applet.mpinterface.domain.pojo.Rundetails;
    import com.baomidou.mybatisplus.extension.service.IService;
    import org.springframework.stereotype.Service;


    @Service
public interface ISpecificService extends IService<Rundetails> {

    // 选择具体的select值
    <#list fields as field>
        public String select${field.name?cap_first}(String id,Integer actionTime);
    </#list>
    }
</#escape>