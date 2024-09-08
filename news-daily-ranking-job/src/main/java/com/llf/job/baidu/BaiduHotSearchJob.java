package com.llf.job.baidu;

import com.llf.dao.entity.HotSearchDO;
import com.llf.service.HotSearchService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.llf.enums.HotSearchEnum.BAIDU;

/**
 * @author llf
 * @version BaiduHotSearchJob.java, 1.0.0
 * @description 百度热搜Java爬虫代码
 * @date 2024.09.07
 */
@Component
@Slf4j
public class BaiduHotSearchJob {

    @Resource
    private HotSearchService hotSearchService;

    /**
     * 定时触发爬虫方法，1个小时执行一次
     */
    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void hotSearch() throws IOException {
        try {
            //获取百度热搜
            String url = "https://top.baidu.com/board?tab=realtime&sa=fyb_realtime_31065";
            List<HotSearchDO> hotSearchDOList = new ArrayList<>();
            Document doc = Jsoup.connect(url).get();
            //标题
            Elements titles = doc.select(".c-single-text-ellipsis");
            //图片
            Elements imgs = doc.select(".category-wrap_iQLoo .index_1Ew5p").next("img");
            //内容
            Elements contents = doc.select(".hot-desc_1m_jR.large_nSuFU");
            //推荐图
            Elements urls = doc.select(".category-wrap_iQLoo a.img-wrapper_29V76");
            //热搜指数
            Elements levels = doc.select(".hot-index_1Bl1a");
            for (int i = 0; i < levels.size(); i++) {
                HotSearchDO hotSearchDO = HotSearchDO.builder().hotSearchResource(BAIDU.getCode()).build();
                //设置文章标题
                hotSearchDO.setHotSearchTitle(titles.get(i).text().trim());
                //设置百度三方ID
                hotSearchDO.setHotSearchId(getHashId(BAIDU.getDesc() + hotSearchDO.getHotSearchTitle()));
                //设置文章封面
                hotSearchDO.setHotSearchCover(imgs.get(i).attr("src"));
                //设置文章摘要
                hotSearchDO.setHotSearchExcerpt(contents.get(i).text().replaceAll("查看更多>", ""));
                //设置文章连接
                hotSearchDO.setHotSearchUrl(urls.get(i).attr("href"));
                //设置热搜热度
                hotSearchDO.setHotSearchHeat(levels.get(i).text().trim());
                //按顺序排名
                hotSearchDO.setHotSearchOrder(i + 1);
                hotSearchDOList.add(hotSearchDO);
            }
            //数据持久化
            hotSearchService.saveCache2DB(hotSearchDOList);
        } catch (IOException e) {
            log.error("获取百度数据异常", e);
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
