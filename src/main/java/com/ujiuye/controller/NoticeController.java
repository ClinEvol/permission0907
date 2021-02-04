package com.ujiuye.controller;

import com.ujiuye.pojo.Employee;
import com.ujiuye.pojo.Notice;
import com.ujiuye.service.NoticeService;
import com.ujiuye.utils.MyFileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    @RequestMapping("/list")
    public List<Notice> list(){
        try {
            return noticeService.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/get")
    public Notice get(int id){
        try {
            return noticeService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //下载附件
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String fileName){
        return MyFileUtils.download(fileName,null);
    }


    @RequestMapping("/save")
    public String save(MultipartFile myfiles,Notice notice, HttpSession session){
        try {
            if(!myfiles.isEmpty()){//有附件
                File file = MyFileUtils.uploadFile(myfiles, null);
                if(file==null){//文件上传失败
                    return "error";
                }
                notice.setPath(file.getName());
            }
            Employee login_session = (Employee)session.getAttribute("LOGIN_SESSION");
            notice.setCreateBy(login_session.getEid());
            noticeService.save(notice);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

}
