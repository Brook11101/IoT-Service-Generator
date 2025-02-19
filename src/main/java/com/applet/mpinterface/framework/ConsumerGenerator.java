package com.applet.mpinterface.framework;

import com.applet.mpinterface.Request.CodeRequest;
import com.applet.mpinterface.service.impl.RundetailsServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConsumerGenerator {
    //需要框架
    public void generateSpring(String proAddr, String appletName, Integer portNum) throws IOException, TemplateException {
        //Freemarker生成代码文件的相关
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(RundetailsServiceImpl.class, "/templates");

        // 获取模板
        Template main_template = cfg.getTemplate("con_main_template.ftl");//src/main/java/com/applet/mpinterface/MpInterfaceApplication.java
        Template pom_template = cfg.getTemplate("con_pom_template.ftl");//pom.xml
        Template yaml_template = cfg.getTemplate("con_yaml_template.ftl");//src/main/resources/application.yaml


        // 创建数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("appletName", appletName);

        //yaml的数据模型
        Map<String, Object> yamldataModel = new HashMap<>();
        yamldataModel.put("portNum", portNum);

        String main_outputPath = proAddr + "/src/main/java/com/" + appletName + "/mpinterface/MpInterfaceApplication.java";
        String pom_outputPath = proAddr + "/pom.xml";
        String yaml_outputPath = proAddr + "/src/main/resources/application.yaml";

        // 创建目录（如果不存在）
        new File(main_outputPath).getParentFile().mkdirs();
        new File(pom_outputPath).getParentFile().mkdirs();
        new File(yaml_outputPath).getParentFile().mkdirs();

        // 创建 Writer，用于写入生成的 Java 文件
        Writer main_writer = new FileWriter(main_outputPath);
        Writer pom_writer = new FileWriter(pom_outputPath);
        Writer yaml_writer = new FileWriter(yaml_outputPath);

        // 填充模板并生成文件
        main_template.process(dataModel, main_writer);
        pom_template.process(dataModel, pom_writer);
        yaml_template.process(yamldataModel, yaml_writer);

        // 关闭 Writer
        main_writer.close();
        pom_writer.close();
        yaml_writer.close();

    }

    //controller
    // 通过javaCode参数和路径传递生成方法和service
    public void generateController(String proAddr, String javacode, String appletName) throws IOException, TemplateException {
        //Freemarker生成代码文件的相关
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(RundetailsServiceImpl.class, "/templates");
        // 获取模板
        Template con_template = cfg.getTemplate("consumer_controller_template.ftl");
        // 创建数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("appletName", appletName);
        dataModel.put("javaCode", javacode);
        String controller_outputPath = proAddr + "/src/main/java/com/" + appletName + "/mpinterface/controller/Controller.java";
        // 创建目录（如果不存在）
        new File(controller_outputPath).getParentFile().mkdirs();
        // 创建 Writer，用于写入生成的 Java 文件
        Writer con_writer = new FileWriter(controller_outputPath);
        // 填充模板并生成文件
        con_template.process(dataModel, con_writer);
        // 关闭 Writer
        con_writer.close();
    }

    //service放在interface里面然后再依赖就比较合理,所以不用另外生成service相关的模块
    @PostMapping("/genConsumer")//用来被前端调用
    public String executeCode(@RequestBody CodeRequest request) throws IOException, TemplateException {
        String javaCode = request.getJavaCode();
        String appletName = request.getAppletName();
        String proAddr = request.getGenAddress();
        Integer portNum = request.getPortNum();
        // 在这里利用模板生成的方法和路径参数生成spring框架

        return "Consumer with Java code generated successfully!";


        //1？默认使用appletA的IService吗
        //2？一般的方法（生成到每个applet里面吗？多两个impl吗？
        //3？方法生成到interface中吗？还是每个都保有，后续测试怎么做

        /*
        1.维护相同协议
            2.去注册中心查
       */
        /*等待执行注册*/
    }
}
