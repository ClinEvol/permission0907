package com.ujiuye.service;

import com.ujiuye.mapper.DeptMapper;
import com.ujiuye.pojo.Dept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;


    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public Dept getByNo(int no) {
        return deptMapper.getByNo(no);
    }

    @Override
    public int save(Dept dept) {
        return deptMapper.save(dept);
    }

    @Override
    public int update(Dept dept) {
        return deptMapper.update(dept);
    }

    @Override
    public int remove(int no) {
        return deptMapper.remove(no);
    }
}
