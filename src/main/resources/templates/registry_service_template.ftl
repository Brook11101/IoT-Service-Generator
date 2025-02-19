<#escape x as x?html>
package iotruleservice;


public interface I${appletName}Service {

    // 选择具体的select值
    <#list fields as field>
        public String ${field.name}(String id,Integer actionTime);
    </#list>
    }
</#escape>