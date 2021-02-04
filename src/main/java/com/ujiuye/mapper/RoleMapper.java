package com.ujiuye.mapper;

import com.ujiuye.pojo.Role;

import java.util.List;

public interface RoleMapper {
    List<Role> list();
    Role getById(int id);
    int remove(int id);
    int save(Role role);
    int update(Role role);
}
