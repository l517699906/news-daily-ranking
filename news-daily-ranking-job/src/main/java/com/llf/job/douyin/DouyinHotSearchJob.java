package com.llf.job.douyin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.llf.dao.entity.HotSearchDO;
import com.llf.service.HotSearchService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.llf.enums.HotSearchEnum.DOUYIN;

/**
 * @author llf
 * @version DouyinHotSearchJob.java, 1.0.0
 * @description 抖音热搜Java爬虫代码
 * @date 2024.09.07
 */
@Component
@Slf4j
public class DouyinHotSearchJob {

    @Resource
    private HotSearchService hotSearchService;

    /**
     * 定时触发爬虫方法，1个小时执行一次
     */
    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void hotSearch() throws IOException {
        try {
            //查询抖音热搜数据
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder().url(
                    "https://www.iesdouyin.com/web/api/v2/hotsearch/billboard/word/").method("GET", null).build();
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = JSONObject.parseObject(response.body().string());
            JSONArray array = jsonObject.getJSONArray("word_list");
            List<HotSearchDO> hotSearchDOList = Lists.newArrayList();
            for (int i = 0, len = array.size(); i < len; i++) {
                //获取知乎热搜信息
                JSONObject object = (JSONObject)array.get(i);
                //构建热搜信息榜
                HotSearchDO hotSearchDO = HotSearchDO.builder().hotSearchResource(DOUYIN.getCode()).build();
                //设置文章标题
                hotSearchDO.setHotSearchTitle(object.getString("word"));
                //设置抖音三方ID
                hotSearchDO.setHotSearchId(getHashId(DOUYIN.getCode() + hotSearchDO.getHotSearchTitle()));
                //设置文章连接
                hotSearchDO.setHotSearchUrl(
                        "https://www.douyin.com/search/" + hotSearchDO.getHotSearchTitle() + "?type=general");
                //设置热搜热度
                hotSearchDO.setHotSearchHeat(object.getString("hot_value"));
                //按顺序排名
                hotSearchDO.setHotSearchOrder(i + 1);
                hotSearchDOList.add(hotSearchDO);
            }
            //数据持久化
            hotSearchService.saveCache2DB(hotSearchDOList);
        } catch (IOException e) {
            log.error("获取抖音数据异常", e);
        }
    }

    /**
     * 根据文章标题获取一个唯一ID
     *
     * @param title 文章标题
     * @return 唯一ID
     */
    public static String getHashId(String title) {
        long seed = title.hashCode();
        Random rnd = new Random(seed);
        return new UUID(rnd.nextLong(), rnd.nextLong()).toString();
    }
}
