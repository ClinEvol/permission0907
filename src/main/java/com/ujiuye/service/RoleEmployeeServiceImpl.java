package com.ujiuye.service;

import com.ujiuye.mapper.RoleEmployeeMapper;
import com.ujiuye.pojo.RoleEmployee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RoleEmployeeServiceImpl  implements RoleEmployeeService{
    @Resource
    private RoleEmployeeMapper roleEmployeeMapper;
    @Override
    public int removeByEmpFk(int emp_fk) {
        return roleEmployeeMapper.removeByEmpFk(emp_fk);
    }

    @Override
    public int saveList(List<RoleEmployee> list) {
        return roleEmployeeMapper.saveList(list);
    }

    @Override
    public List<RoleEmployee> listByEmpFk(int emp_fk) {
        return roleEmployeeMapper.listByEmpFk(emp_fk);
    }
}
