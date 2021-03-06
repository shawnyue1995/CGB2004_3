package com.cy.pj.sys.service;

import com.cy.pj.common.pojo.PageObject;
import com.cy.pj.sys.pojo.SysLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysLogServiceTest {
    @Autowired
    private SysLogService sysLogService;

    @Test
    public void testFindPageObjects() {
        PageObject<SysLog> pageObject = sysLogService.findPageObjects("admin", 1);
        System.out.println(pageObject);
    }
}
