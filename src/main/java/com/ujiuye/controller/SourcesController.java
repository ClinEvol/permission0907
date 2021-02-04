package com.ujiuye.controller;

import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Sources;
import com.ujiuye.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/sources")
@RestController
public class SourcesController {
    @Autowired
    private SourcesService sourcesService;

    @RequestMapping("/simpledata")
    public List<Sources> simpledata(){
        try {
            return sourcesService.simpleData();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/standerdata")
    public List<Sources> standerdata(){
        try {
            return sourcesService.standerData();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getbyid")
    public Sources getbyid(int id){
        try {
            return sourcesService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/save")
    public String save(Sources sources){
        try {
            sourcesService.save(sources);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    @RequestMapping("/update")
    public String update(Sources sources){
        try {
            sourcesService.update(sources);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/remove")
    public String remove(int id){
        try {
            sourcesService.remove(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

}
