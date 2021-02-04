package com.ujiuye.service;

import com.ujiuye.mapper.SourcesMapper;
import com.ujiuye.pojo.Sources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SourcesServiceImpl implements SourcesService {

    @Resource
    private SourcesMapper sourcesMapper;

    @Override
    public int save(Sources sources) {
        return sourcesMapper.save(sources);
    }

    @Override
    public int update(Sources sources) {
        return sourcesMapper.update(sources);
    }

    @Override
    public int remove(int id) {
        return sourcesMapper.remove(id);
    }

    @Override
    public Sources getById(int id) {
        return sourcesMapper.getById(id);
    }

    @Override
    public List<Sources> simpleData() {
        return sourcesMapper.listAll();
    }

    //难点  标准数据格式
    @Override
    public List<Sources> standerData() {
        //1找到顶层菜单
        List<Sources> sourcesList = sourcesMapper.listByPid(0);
        if(sourcesList!=null && sourcesList.size()>0){
            for (Sources source : sourcesList) { //oa办公协同系统
                findChildren(source);
            }
        }
        return sourcesList;
    }

    private void findChildren(Sources source){
        //查oa办公协同系统  的子菜单
        List<Sources> sourcesList = sourcesMapper.listByPid(source.getId());
        if(sourcesList!=null && sourcesList.size()>0){
            source.setChildren(sourcesList);//把查到的子菜存入当前菜对象的Children属性
            //继续查子菜的子菜单
            for (Sources subSources : sourcesList) {//遍历这个子菜单集合
                findChildren(subSources);//递归
            }
        }
    }
}
