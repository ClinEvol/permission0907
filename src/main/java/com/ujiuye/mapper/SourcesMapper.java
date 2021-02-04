package com.ujiuye.mapper;

import com.ujiuye.pojo.Sources;

import java.util.List;

public interface SourcesMapper {
    int save(Sources sources);
    int update(Sources sources);
    int remove(int id);
    Sources getById(int id);
    List<Sources> listAll();
    List<Sources> listByPid(int pid);
}
