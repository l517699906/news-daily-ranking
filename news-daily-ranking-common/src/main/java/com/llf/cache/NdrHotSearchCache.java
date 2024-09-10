package com.llf.cache;

import com.google.common.collect.Maps;
import com.llf.model.HotSearchDetailDTO;

import java.util.Map;

public class NdrHotSearchCache {

    /**
     * 热搜缓存
     */
    public static final Map<String, HotSearchDetailDTO> CACHE_MAP = Maps.newHashMap();

}
