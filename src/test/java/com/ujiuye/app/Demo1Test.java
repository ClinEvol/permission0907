package com.ujiuye.app;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Demo1Test {

    public static void main(String[] args) {
        System.out.println("sssss");
    }

    @Test
    public void demo1() throws IOException {
        //操作Excel   目前这个文件是空的
        File file=new File("/Users/ClinEvol/Desktop/907/demo/demoxls.xlsx");
        //创建工作簿
        XSSFWorkbook workbook=new XSSFWorkbook();
        //创建工作表
        XSSFSheet sheet = workbook.createSheet("我的通讯录");
        //创建行
        XSSFRow row = sheet.createRow(0);
        //设置单元格
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("电话");

        //集合    数据库的数据
        List<User> list=new ArrayList<>();
        list.add(new User("张三","13888889999"));
        list.add(new User("李四","13888886699"));
        list.add(new User("王五","13888889988"));
        list.add(new User("赵六","13888886666"));
        for (int i=0;i<list.size();i++) {
            XSSFRow dataRow = sheet.createRow(i+1);
            //设置单元格
            dataRow.createCell(0).setCellValue(list.get(i).getName());
            dataRow.createCell(1).setCellValue(list.get(i).getTel());
        }
        //把内容写到文件里面去
        workbook.write(new FileOutputStream(file));
        System.out.println("ok");


    }
}
