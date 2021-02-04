package com.ujiuye.service;

import com.ujiuye.pojo.Sources;

import java.util.List;

public interface SourcesService {
    int save(Sources sources);
    int update(Sources sources);
    int remove(int id);
    Sources getById(int id);
    List<Sources> simpleData();
    List<Sources> standerData();
}
