package com.ujiuye.service;

import com.ujiuye.pojo.Role;

import java.util.List;

public interface RoleService {
    List<Role> list();
    Role getById(int id);
    int remove(int id);
    int save(Role role);
    int update(Role role);
}
