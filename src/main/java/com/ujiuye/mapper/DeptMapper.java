package com.ujiuye.mapper;

import com.ujiuye.pojo.Dept;

import java.util.List;

public interface DeptMapper {
    List<Dept> list();
    Dept getByNo(int no);
    int save(Dept dept);
    int update(Dept dept);
    int remove(int no);
}
