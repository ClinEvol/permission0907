package com.ujiuye.service;

import com.ujiuye.pojo.Notice;

import java.util.List;

public interface NoticeService {
    int save(Notice notice);
    List<Notice> list();
    Notice getById(int id);
}
