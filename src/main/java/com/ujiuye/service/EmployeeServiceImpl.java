package com.ujiuye.service;

import com.github.pagehelper.PageHelper;
import com.ujiuye.mapper.EmployeeMapper;
import com.ujiuye.pojo.AgeDistribution;
import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.RoleEmployee;
import com.ujiuye.pojo.SexNumber;
import com.ujiuye.utils.MD5Utils;
import com.ujiuye.utils.PinyinUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private RoleEmployeeService roleEmployeeService;
    @Override
    public List<Employee> list() {
        return employeeMapper.list();
    }

    @Override
    public Employee getById(int id) {
        return employeeMapper.getById(id);
    }

    @Override
    public int save(Employee employee) {
        //用户的问题
        while(true){
            //生成用户名
            String userName = createUserName(employee.getEname());
            //查一下数据库
            Employee employee1 = employeeMapper.getByUserName(userName);
            if(employee1==null){
                employee.setUsername(userName);
                break;
            }
        }
        //密码
        String md5 = MD5Utils.stringToMD5("123456");
        employee.setPassword(md5);
        //添加员工
        employeeMapper.save(employee);

        //去除无效的值
        List<RoleEmployee> roles = removeNull(employee.getRoles(),employee.getEid());
        //添加中间表
        roleEmployeeService.saveList(roles);
        return 1;
    }

    //生成用户名
    private String createUserName(String ename){
        String username = PinyinUtil.getPinyinInitials(ename).toLowerCase();
        String random = String.valueOf(Math.random()).substring(3, 7);
        username=username+random;
        return username;
    }

    //去掉空的role_fk的对象
    private List<RoleEmployee> removeNull(List<RoleEmployee> roles,int eid){
        List<RoleEmployee> list=new ArrayList<>();
        for (RoleEmployee roleEmployee : roles) {
            if(roleEmployee.getRole_fk()!=null){
                roleEmployee.setEmp_fk(eid);
                list.add(roleEmployee);
            }
        }
        return list;
    }

    @Override
    public int update(Employee employee) {
        //修改员工表
        return employeeMapper.update(employee);
    }

    @Override
    public int updateAndRole(Employee employee) {
        List<RoleEmployee> roles = removeNull(employee.getRoles(),employee.getEid());
        //删除中间表
        roleEmployeeService.removeByEmpFk(employee.getEid());
        //重新添加
        roleEmployeeService.saveList(roles);
        //修改员工表
        return employeeMapper.update(employee);
    }

    @Override
    public int remove(int id) {
        //删除中间表
        roleEmployeeService.removeByEmpFk(id);
        return employeeMapper.remove(id);
    }

    @Override
    public Employee login(String username, String password) {
        Employee employee = employeeMapper.getByUserName(username);
        if(employee==null){//用户名不正确
            return null;
        }
        //查到该用户名   两个密码比较
        if(!employee.getPassword().equals(MD5Utils.stringToMD5(password))){
            return null;
        }
        //用户名和密码正确的
        return employee;
    }

    @Override
    public Employee getByUserName(String username) {
        return employeeMapper.getByUserName(username);
    }


    //把集合写excel
    public File ListToExcel(int pageNum){

        PageHelper.startPage(pageNum,6);
        List<Employee> list = employeeMapper.list();

        File file=new File("");
        String path=file.getAbsolutePath()+"/src/main/webapp/download/";
        File downloadFile=new File(path+"用户信息第"+pageNum+"页数据.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("第一页");
        XSSFRow sheetRow = sheet.createRow(0);
        sheetRow.createCell(0).setCellValue("员工编号");
        sheetRow.createCell(1).setCellValue("姓名");
        sheetRow.createCell(2).setCellValue("性别");
        sheetRow.createCell(3).setCellValue("年龄");
        sheetRow.createCell(4).setCellValue("电话");
        sheetRow.createCell(5).setCellValue("入职日期");
        sheetRow.createCell(6).setCellValue("身份证号码");
        sheetRow.createCell(7).setCellValue("用户名");
        sheetRow.createCell(8).setCellValue("部门");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        for (int i=0;i<list.size();i++) {
            Employee employee = list.get(i);
            XSSFRow row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(employee.getEid());
            row.createCell(1).setCellValue(employee.getEname());
            row.createCell(2).setCellValue(employee.getEsex());
            row.createCell(3).setCellValue(employee.getEage());
            row.createCell(4).setCellValue(employee.getTelephone());
            String format = simpleDateFormat.format(employee.getHiredate());
            row.createCell(5).setCellValue(format);
            row.createCell(6).setCellValue(employee.getPnum());
            row.createCell(7).setCellValue(employee.getUsername());
            row.createCell(8).setCellValue(employee.getDept().getDname());
        }
        try {
            workbook.write(new FileOutputStream(downloadFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return downloadFile;
    }


    @Override
    public List<SexNumber> sexNumberList() {
        return employeeMapper.sexNumberList();
    }

    @Override
    public List<AgeDistribution> AgeDistributionList() {
        List<AgeDistribution> list=new ArrayList<>();
        list.add(new AgeDistribution(employeeMapper.countAge(18, 29),"青年组"));
        list.add(new AgeDistribution(employeeMapper.countAge(30, 39),"中青年组"));
        list.add(new AgeDistribution(employeeMapper.countAge(40, 49),"中年组"));
        list.add(new AgeDistribution(employeeMapper.countAge(50, 60),"中老年组"));
        return list;
    }


}
