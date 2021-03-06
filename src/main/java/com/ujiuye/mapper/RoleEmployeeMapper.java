package com.ujiuye.mapper;

import com.ujiuye.pojo.RoleEmployee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleEmployeeMapper {
    //通过员工id删除
    int removeByEmpFk(int emp_fk);
    //批量添加
    int saveList(@Param("list") List<RoleEmployee> list);
    //通过员工id查
    List<RoleEmployee> listByEmpFk(int emp_fk);
}
