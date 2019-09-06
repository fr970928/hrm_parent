package com.fengrui.hrm.cache;

import com.alibaba.fastjson.JSONArray;
import com.fengrui.hrm.client.RedisClient;
import com.fengrui.hrm.domain.CourseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseTypeCache {

    @Autowired
    private RedisClient redisClient;

    private static  final  String TYPETREEDATA_IN_REDIS = "typetreedata_in_redis";

    public List<CourseType> getCourseTypes(){
        String s = redisClient.get(TYPETREEDATA_IN_REDIS);
        return JSONArray.parseArray(s, CourseType.class);
    }

    public void setCourseTypes(List<CourseType> courseTypesDb){
        String s = JSONArray.toJSONString(courseTypesDb);
        redisClient.set(TYPETREEDATA_IN_REDIS, s);
    }
}
