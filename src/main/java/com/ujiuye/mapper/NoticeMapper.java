package com.ujiuye.mapper;

import com.ujiuye.pojo.Notice;

import java.util.List;

public interface NoticeMapper {
    int save(Notice notice);
    List<Notice> list();
    Notice getById(int id);
}
