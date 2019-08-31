package com.fengrui.hrm;

import com.fengrui.hrm.domain.Systemdictionary;
import com.fengrui.hrm.service.ISystemdictionaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SysmanageService9001Application.class)
public class MyTest {

    @Autowired
    private ISystemdictionaryService systemdictionaryService;

    @Test
    public void test() throws  Exception{
        for (Systemdictionary systemdictionary : systemdictionaryService.selectList(null)) {
            System.out.println(systemdictionary);
        }
    }
    
}
