package com.llf.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotSearchDTO {

    /**
     * 物理主键
     */
    private Long id;

    /**
     * 热搜标题
     */
    private String hotSearchTitle;

    /**
     * 热搜作者
     */
    private String hotSearchAuthor;

    /**
     * 热搜来源
     */
    private String hotSearchResource;

    /**
     * 热搜排名
     */
    private Integer hotSearchOrder;

    /**
     * 热搜链接
     */
    private String hotSearchUrl;

    /**
     * 热搜封面
     */
    private String hotSearchCover;

    /**
     * 热搜作者头像
     */
    private String hotSearchAuthorAvatar;

    /**
     * 热搜ID
     */
    private String hotSearchId;

    /**
     * 热搜摘录
     */
    private String hotSearchExcerpt;

    /**
     * 热搜热度
     */
    private String hotSearchHeat;
}
