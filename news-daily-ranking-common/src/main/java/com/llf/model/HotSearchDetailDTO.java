package com.llf.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class HotSearchDetailDTO {

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 热搜数据
     */
    private List<HotSearchDTO> hotSearchDTOList;
}
