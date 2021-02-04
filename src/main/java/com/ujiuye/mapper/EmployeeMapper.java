package com.ujiuye.mapper;

import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.SexNumber;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> list();
    Employee getById(int id);

    Employee getByUserName(String username);

    int save(Employee employee);
    int update(Employee employee);
    int remove(int id);

    List<SexNumber> sexNumberList();

    int countAge(@Param("begin") int begin, @Param("end") int end);


}
