package com.llf.page;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

    /**
     * 查询到的总条数
     */
    private long totalItems;

    /**
     * 总页数
     */
    private long totalPages;

    /**
     * 当前页,PageNum
     */
    private int currentPage;

    /**
     * 每页条数,PageSize
     */
    private int itemsPerPage;

    /**
     * 分页结果
     */
    private List<T> value;

    private Page() {

    }

    /**
     * 返回一个空的分页
     *
     * @param <T>
     * @return
     */
    public static <T> Page<T> emptyPage() {
        Page<T> result = new Page<T>();
        result.setValue(new ArrayList<T>());
        return result;
    }

    /**
     * 根据完整的项目列表创建假分页。
     *
     * @param list     完整的项目列表，用于分页。
     * @param pageNum  要检索的页码。
     * @param pageSize 每页的项目数目。
     * @return 返回指定页面的Page对象，包含对应的项目列表。
     */
    public static <T> Page<T> startPage(List<T> list, int pageNum, int pageSize) {
        int totalItems = list.size();
        int totalPages = (int)Math.ceil((double)pageNum / pageSize);

        // 计算请求页面上第一个项目的索引
        int startIndex = (pageNum - 1) * pageSize;
        // 确保不会超出列表范围
        startIndex = Math.max(startIndex, 0);

        // 计算请求页面上最后一个项目的索引
        int endIndex = startIndex + pageSize;
        // 确保不会超出列表范围
        endIndex = Math.min(endIndex, totalItems);

        // 从完整列表中提取当前页面的子列表
        List<T> pageItems = list.subList(startIndex, endIndex);

        // 创建并填充Page对象
        Page<T> page = new Page<>();
        page.setCurrentPage(pageNum);
        page.setItemsPerPage(pageSize);
        page.setTotalItems(totalItems);
        page.setTotalPages(totalPages);
        page.setValue(pageItems);
        return page;
    }

    /**
     * 真分页数据转换
     *
     * @param doList  原始DO列表
     * @param dtoList 返回值DTO列表
     * @param <T>
     * @return 分页数据
     */
    public static <T> Page<T> resetPage(List doList, List<T> dtoList) {
        if (null == doList) {
            //如果doList为空，返回空分页
            return emptyPage();
        }
        Page<T> result = new Page();
        //获取PageHelper的分页属性
        PageInfo<T> pageInfo = new PageInfo(doList);
        result.setTotalPages(pageInfo.getPages());
        result.setCurrentPage(pageInfo.getPageNum());
        result.setItemsPerPage(pageInfo.getPageSize());
        result.setTotalItems(pageInfo.getTotal());
        //设置返回值列表
        result.setValue(dtoList);
        return result;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public List<T> getValue() {
        return value;
    }

    public void setValue(List<T> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Page{" + "totalItems=" + totalItems + ", totalPages=" + totalPages + ", currentPage=" + currentPage
                + ", itemsPerPage=" + itemsPerPage + ", value=" + value + '}';
    }

}
