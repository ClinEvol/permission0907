package com.ujiuye.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.pojo.AgeDistribution;
import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.SexNumber;
import com.ujiuye.service.EmployeeService;
import com.ujiuye.utils.MyFileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/list")
    public PageInfo list(
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum){
        try {
            PageHelper.startPage(pageNum,6);
            List<Employee> list = employeeService.list();
            PageInfo<Employee> pageInfo=new PageInfo<>(list);
            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/get")
    public Employee get(int id){
        try {
            Employee role = employeeService.getById(id);
            return role;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/save")
    public String save(Employee employee){
        try {
            employeeService.save(employee);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/update")
    public String update(Employee role){
        try {
            employeeService.updateAndRole(role);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/remove")
    public String remove(int id){
        try {
            employeeService.remove(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }


    //上传并回显
    @RequestMapping("/upload")
    public String upload(MultipartFile myLogo){
        File file = MyFileUtils.uploadFile(myLogo, null);
        if(file==null){
            return "fail";
        }
        //返回头像路径
        return "/upload/"+file.getName();
    }

    @RequestMapping("/updateLogon")
    public String updateLogon(String pic,HttpSession session){
        try {
            Employee employee=(Employee)session.getAttribute("LOGIN_SESSION");
            employee.setPic(pic);
            employeeService.update(employee);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    //保存头像路径

    @RequestMapping("/getCurrentUser")
    public Employee getCurrentUser(HttpSession session){
        Employee employee=(Employee)session.getAttribute("LOGIN_SESSION");
        return employeeService.getById(employee.getEid());
    }

    @RequestMapping("/downloadExcel")
    public ResponseEntity<byte[]> downloadExcel(
            @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum) throws IOException {
        File file = employeeService.ListToExcel(pageNum);

        return MyFileUtils.download(file);

    }


    //性别分布
    @RequestMapping("/sexNumberList")
    public List<SexNumber> sexNumberList(){
        try {
            return employeeService.sexNumberList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //年龄分布
    @RequestMapping("/ageDistributionList")
    public List<AgeDistribution> AgeDistributionList(){
        try {
            return employeeService.AgeDistributionList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }






}
