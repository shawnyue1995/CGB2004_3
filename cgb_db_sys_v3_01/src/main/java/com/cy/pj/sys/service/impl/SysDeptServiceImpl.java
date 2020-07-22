package com.cy.pj.sys.service.impl;

import com.cy.pj.common.annotation.ClearCache;
import com.cy.pj.common.annotation.RequiredCache;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.pojo.Node;
import com.cy.pj.common.util.AssertUtil;
import com.cy.pj.sys.dao.SysDeptDao;
import com.cy.pj.sys.pojo.SysDept;
import com.cy.pj.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptDao sysDeptDao;

    @RequiredCache//使用自己写的注解描述方法
    @Override
    public List<Map<String, Object>> findObjects() {
        List<Map<String, Object>> list = sysDeptDao.findObjects();
        AssertUtil.isArgumentValid(list == null || list.size() == 0, "没有部门信息");
        return list;
    }

    @Override
    public List<Node> findZTreeNodes() {
        List<Node> list = sysDeptDao.findZTreeNodes();
        AssertUtil.isArgumentValid(list == null || list.size() == 0, "没有部门信息");
        return list;
    }

    @ClearCache
    @Override
    public int saveObject(SysDept entity) {
        //1.合法验证
        AssertUtil.isArgumentValid(entity == null, "保存对象不能为空");
        AssertUtil.isArgumentValid(StringUtils.isEmpty(entity.getName()), "部门不能为空");
        //2.保存对象
        int rows = sysDeptDao.insertObject(entity);
        /*AssertUtil.isResultValid(rows==1,"save error");*/
        //3.返回数据
        return rows;
    }

    @ClearCache
    @Override
    public int updateObject(SysDept entity) {
        //1.合法验证
        AssertUtil.isArgumentValid(entity == null, "保存对象不能为空");
        AssertUtil.isArgumentValid(StringUtils.isEmpty(entity.getName()), "部门不能为空");
        int rows;
        //2.更新数据
        try {
            rows = sysDeptDao.updateObject(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("更新失败");
        }
        return rows;
    }

    @ClearCache
    @Override
    public int deleteObject(Integer id) {
        //1.合法性验证
        AssertUtil.isArgumentValid(id == null || id <= 0, "数据不合法，id=" + id);
        //2.执行删除操作
        //2.判定此id对应的菜单是否有子元素
        int childCount = sysDeptDao.getChildCount(id);
        AssertUtil.isArgumentValid(childCount > 0, "此元素有子元素，不允许删除");
        //if(userCount>0)
        //throw new ServiceException("此部门有员工，不允许对部门进行删除");
        //2.2判定此部门是否已经被用户使用,假如有则拒绝删除
        //2.3执行删除操作
        int rows = sysDeptDao.deleteObject(id);
        if (rows == 0)
            throw new ServiceException("此信息可能已经不存在");
        return rows;
    }
}
