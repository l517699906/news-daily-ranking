package com.llf.job.zhihu;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;

import com.llf.dao.entity.HotSearchDO;
import com.llf.service.HotSearchService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.llf.enums.HotSearchEnum.ZHIHU;

/**
 * @author llf
 * @version DouyinHotSearchJob.java, 1.0.0
 * @description 知乎热搜Java爬虫代码
 * @date 2024.09.10
 */
@Component
@Slf4j
public class ZhihuHotSearchJob {

    @Autowired
    private HotSearchService hotSearchService;

    /**
     * 定时触发爬虫方法，1个小时执行一次
     */
    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void hotSearch() throws IOException {
        try {
            //查询知乎热搜数据
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder().url("https://www.zhihu.com/api/v3/feed/topstory/hot-lists/total")
                .method("GET", null).build();
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            JSONObject jsonObject = JSON.parseObject(response.body().string());
            JSONArray array = jsonObject.getJSONArray("data");
            List<HotSearchDO> hotSearchDOList = Lists.newArrayList();
            for (int i = 0, len = array.size(); i < len; i++) {
                //获取知乎热搜信息
                JSONObject object = (JSONObject)array.get(i);
                JSONObject target = object.getJSONObject("target");
                //构建热搜信息榜
                HotSearchDO hotSearchDO = HotSearchDO.builder().hotSearchResource(ZHIHU.getCode()).build();
                //设置知乎三方ID
                hotSearchDO.setHotSearchId(target.getString("id"));
                //设置文章连接
                hotSearchDO.setHotSearchUrl("https://www.zhihu.com/question/" + hotSearchDO.getHotSearchId());
                //设置文章标题
                hotSearchDO.setHotSearchTitle(target.getString("title"));
                //设置作者名称
                hotSearchDO.setHotSearchAuthor(target.getJSONObject("author").getString("name"));
                //设置作者头像
                hotSearchDO.setHotSearchAuthorAvatar(target.getJSONObject("author").getString("avatar_url"));
                //设置文章摘要
                hotSearchDO.setHotSearchExcerpt(target.getString("excerpt"));
                //设置热搜热度
                hotSearchDO.setHotSearchHeat(object.getString("detail_text").replace("热度", ""));
                //按顺序排名
                hotSearchDO.setHotSearchOrder(i + 1);
                hotSearchDOList.add(hotSearchDO);
            }
            //数据持久化
            hotSearchService.saveCache2DB(hotSearchDOList);
        } catch (IOException e) {
            log.error("获取知乎数据异常", e);
        }
    }

}

