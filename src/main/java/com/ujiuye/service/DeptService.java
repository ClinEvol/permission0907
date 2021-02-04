package com.ujiuye.service;

import com.ujiuye.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();
    Dept getByNo(int no);
    int save(Dept dept);
    int update(Dept dept);
    int remove(int no);
}
