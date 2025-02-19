package com.${appletName}.mpinterface;
import com.${appletName}.mpinterface.service.impl.SpecificServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = MpInterfaceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MpInterfaceApplicationTest {

@Autowired
private  SpecificServiceImpl specificService;
//
//@Test
//void contextLoads() {
//}
@Test
public  void testGeneratedService()
{
String a = specificService.selectBrightness("dWpBihQN", 1702953840);
System.out.println("获取到"+a);
}

}