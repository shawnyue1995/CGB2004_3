package com.cy.pj.sys.service;

import java.util.List;
import java.util.Map;

public interface SysMenuService {
    List<Map<String, Object>> findObjects();

    int deleteObject(Integer id);

}
