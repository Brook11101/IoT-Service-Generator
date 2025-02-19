package com.applet.mpinterface.framework;

import com.applet.mpinterface.service.impl.RundetailsServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpringGenerator {

    public void generateSpring(String proAddr, String appletName, Integer portNum) throws IOException, TemplateException {
        //Freemarker生成代码文件的相关
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(RundetailsServiceImpl.class, "/templates");

        // 获取模板
        Template main_template = cfg.getTemplate("main_template.ftl");//src/main/java/com/applet/mpinterface/MpInterfaceApplication.java
        Template pom_template = cfg.getTemplate("pom_template.ftl");//pom.xml
        Template yaml_template = cfg.getTemplate("yaml_template.ftl");//src/main/resources/application.yaml


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
}
