package com.stanbill.service;
/**
 * RecordService消费记录业务类只有一个方法add
 * 根据消费金额，消费分类，备注和日期添加一条消费记录
 */

import java.util.Date;

import com.stanbill.dao.RecordDAO;
import com.stanbill.entity.Category;
import com.stanbill.entity.Record;

public class RecordService {
    RecordDAO recordDao = new RecordDAO();
    public void add(int spend, Category c, String comment,Date date){
        Record r = new Record();
        r.spend = spend;
        r.cid = c.id;
        r.comment = comment;
        r.date = date;
        recordDao.add(r);
    }
}

