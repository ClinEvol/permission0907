package com.ujiuye.service;

import com.ujiuye.pojo.AgeDistribution;
import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.SexNumber;

import java.io.File;
import java.util.List;

public interface EmployeeService {
    List<Employee> list();
    Employee getById(int id);
    int save(Employee employee);
    int update(Employee employee);

    int updateAndRole(Employee employee);

    int remove(int id);

    Employee login(String username,String password);
    File ListToExcel(int pageNum);
    Employee getByUserName(String username);

    //性别分布
    List<SexNumber> sexNumberList();
    //年龄分布
    List<AgeDistribution> AgeDistributionList();
}
