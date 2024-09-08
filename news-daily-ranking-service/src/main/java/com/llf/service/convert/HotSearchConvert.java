package com.llf.service.convert;

import com.llf.dao.entity.HotSearchDO;
import com.llf.model.HotSearchDTO;

public class HotSearchConvert {

    public static HotSearchDTO toDTOWhenQuery(HotSearchDO hotSearchDO) {
        return HotSearchDTO.builder()
                //热搜作者
                .hotSearchAuthor(hotSearchDO.getHotSearchAuthor())
                //热搜作者头像
                .hotSearchAuthorAvatar(hotSearchDO.getHotSearchAuthorAvatar())
                //热搜封面
                .hotSearchCover(hotSearchDO.getHotSearchCover())
                //热度
                .hotSearchHeat(hotSearchDO.getHotSearchHeat())
                //热搜id
                .hotSearchId(hotSearchDO.getHotSearchId())
                //热搜摘要
                .hotSearchExcerpt(hotSearchDO.getHotSearchExcerpt())
                //热搜排序
                .hotSearchOrder(hotSearchDO.getHotSearchOrder())
                //热搜来源
                .hotSearchResource(hotSearchDO.getHotSearchResource())
                //热搜标题
                .hotSearchTitle(hotSearchDO.getHotSearchTitle())
                //热搜链接
                .hotSearchUrl(hotSearchDO.getHotSearchUrl())
                //物理主键
                .id(hotSearchDO.getId()).build();
    }
}
