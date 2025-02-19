package com.applet.mpinterface.service;

import com.applet.mpinterface.framework.SpringGenerator;

import com.applet.mpinterface.framework.ConsumerGenerator;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IRundetailsServiceTest {
    @Autowired
    private IRundetailsService rundetailsService;

    @Autowired
    private SpringGenerator generator;
    @Autowired
    private ConsumerGenerator consumerGenerator;
    @Autowired
    private ISpecificGenerator specificGenerator;

    @Test
    public void generateAppletSpring() throws TemplateException, IOException {
        //生成到有的框架中
        generator.generateSpring("D:\\preStage\\IoTRPC\\appletA", "appletA", 9090);//appletA生成时用的
        generator.generateSpring("D:\\preStage\\IoTRPC\\appletB", "appletB", 9091);

    }

    @Test
    public void generateAppletFunction() {
        //改成生成到现有目录下面
        specificGenerator.generateAllDetailgetterCode("dWpBihQN", 1702953840, "D:\\preStage\\IoTRPC\\appletA" + "/src/main/java/com/appletA/mpinterface", "appletA");//appletA生成时用的
        specificGenerator.generateAllDetailgetterCode("D65dGVxZ", 1702964040, "D:\\preStage\\IoTRPC\\appletB" + "/src/main/java/com/appletB/mpinterface", "appletB");
    }


    @Test
    public void generateConsumer() throws TemplateException, IOException {
        //生成到有的框架中
        consumerGenerator.generateSpring("D:\\preStage\\IoTRPC\\ConsumerA", "ConsumerA", 9092);

        String javaCode = "@RemoteCallReference\n" +
                "private IappletAService appletAService;\n" +
                "\n" +
                "@GetMapping(\"SwitchName\")\n" +
                "public String SwitchName() {\n" +
                "return appletAService.SwitchName(\"dWpBihQN\", 1702953840);\n" +
                "}";

        consumerGenerator.generateController("D:\\preStage\\IoTRPC\\ConsumerA", javaCode, "ConsumerA");
    }


}