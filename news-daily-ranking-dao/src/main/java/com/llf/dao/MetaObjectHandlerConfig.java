package com.llf.dao;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Date;

@Configuration
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = Calendar.getInstance().getTime();
        this.fillStrategy(metaObject, "gmtCreate", date);
        this.fillStrategy(metaObject, "gmtModified", date);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = Calendar.getInstance().getTime();
        this.setFieldValByName("gmtModified", date, metaObject);
    }
}
