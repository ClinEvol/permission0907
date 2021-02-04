package com.ujiuye.service;

import com.ujiuye.mapper.NoticeMapper;
import com.ujiuye.pojo.Notice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;
    @Override
    public int save(Notice notice) {
        notice.setDate(new Date());
        return noticeMapper.save(notice);
    }

    @Override
    public List<Notice> list() {
        return noticeMapper.list();
    }

    @Override
    public Notice getById(int id) {
        return noticeMapper.getById(id);
    }
}
