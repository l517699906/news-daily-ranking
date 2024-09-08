package com.llf.service;

import com.llf.dao.entity.HotSearchDO;
import com.llf.model.HotSearchDTO;
import com.llf.page.Page;

import java.util.List;

public interface HotSearchService {

    /**
     * 保存热搜数据到数据库
     *
     * @param hotSearchDOList 数据库
     * @return 操作状态
     */
    Boolean saveCache2DB(List<HotSearchDO> hotSearchDOList);


    /**
     * 根据标题查询热搜
     *
     * @param title    标题
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 热搜
     */
    Page<HotSearchDTO> pageQueryHotSearchByTitle(String title, Integer pageNum, Integer pageSize);
}
