package com.ujiuye.service;

import com.ujiuye.mapper.RoleMapper;
import com.ujiuye.pojo.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Override
    public List<Role> list() {
        return roleMapper.list();
    }

    @Override
    public Role getById(int id) {
        return roleMapper.getById(id);
    }

    @Override
    public int remove(int id) {
        return roleMapper.remove(id);
    }

    @Override
    public int save(Role role) {
        return roleMapper.save(role);
    }

    @Override
    public int update(Role role) {
        return roleMapper.update(role);
    }
}
