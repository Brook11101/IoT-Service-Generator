package com.applet.mpinterface.service;

import com.applet.mpinterface.domain.pojo.Rundetails;
import com.applet.mpinterface.framework.SpringGenerator;

import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IRundetailsServiceTest {
    @Autowired
    private IRundetailsService rundetailsService;
//    @Autowired
//    private  ISpecificService specificService;
    @Autowired
    private SpringGenerator generator;
    @Autowired
    private ISpecificGenerator specificGenerator;
    @Test
    public void testGen() throws TemplateException, IOException {
        generator.generateSpring("D:\\preStage\\IoTRPC\\test","appletA",8080);
    }

    @Test
    public void generateJavaCode()
    {
        specificGenerator.generateAllDetailgetterCode("dWpBihQN", 1702953840,"D:\\preStage\\IoTRPC\\test"+"/src/main/java/com/appletA/mpinterface","appletA");
    }
//    @Test
//    public  void testGeneratedService()
//    {
//        String a = specificService.selectBrightness("dWpBihQN", 1702953840);
//        System.out.println("获取到"+a);
//    }
    //    @Test
//    public void testSearchnew()
//    {
//        Rundetails rundetails = rundetailsService.getLatestRecordById("Vwex4QJV");
//        System.out.println(rundetails);
//
//    }
//    @Test
//    public void testSearchnewList()
//    {
//        List<Rundetails> rundetails = rundetailsService.getLatestRecordsById("Vwex4QJV");
//        System.out.println(rundetails);
//
//    }

}