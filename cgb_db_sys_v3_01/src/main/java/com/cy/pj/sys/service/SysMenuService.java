package com.cy.pj.sys.service;

import com.cy.pj.common.pojo.Node;
import com.cy.pj.sys.pojo.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuService {

    int updateObject(SysMenu entity);

    int saveObject(SysMenu entity);

    List<Node> findZtreeMenuNodes();

    List<Map<String, Object>> findObjects();

    /**
     * 基于菜单id删除菜单元素
     *
     * @param id
     * @return
     */
    int deleteObject(Integer id);

}
