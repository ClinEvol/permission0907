package com.ujiuye.controller;

import com.ujiuye.pojo.Role;
import com.ujiuye.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @RequestMapping("/list")
    public List<Role> list(){
        try {
            List<Role> list = roleService.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/get")
    public Role get(int id){
        try {
            Role role = roleService.getById(id);
            return role;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/save")
    public String save(Role role){
        try {
            roleService.save(role);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/update")
    public String update(Role role){
        try {
            roleService.update(role);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/remove")
    public String remove(int id){
        try {
            roleService.remove(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }


    @RequestMapping("/removeList")
    public String removeList(Integer[] ids){
        for (Integer id : ids) {
            System.out.println(id);
        }
        return "";
    }

}
