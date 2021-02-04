package com.ujiuye.controller;

import com.ujiuye.pojo.Dept;
import com.ujiuye.service.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController    //这个类下面的方法都是异步方法是@ResponseBody和Controller的结合版
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;


    /**
     * 查询所有
     * @return  部门集合
     */
    @RequestMapping("/list")
    public List<Dept> list(){    //ctrl+alt+t
        try {
            return deptService.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 查询单个
     * @param no  部门主键
     * @return   部门对象
     */
    @RequestMapping("/get")
    public Dept get(int no){
        try {
            return deptService.getByNo(no);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加部门
     * @param dept   部门对象
     * @return    成功返回success，失败返回fail
     */
    @RequestMapping("/save")
    public String save(Dept dept){
        try {
            deptService.save(dept);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 修改部门信息
     * @param dept 部门对象
     * @return 成功返回success，失败返回fail
     */
    @RequestMapping("/update")
    public String update(Dept dept){
        try {
            deptService.update(dept);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 删除
     * @param no  部门主键
     * @return  成功返回success，失败返回fail
     */
    @RequestMapping("/remove")
    public String remove(int no){
        try {
            deptService.remove(no);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }


}
