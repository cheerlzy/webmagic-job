package com.gupiao.oanews;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import com.gupiao.oanews.OaNewsCrawlerProcessor.News;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author zhuxing [zhuxing1@xiaoniu66.com, xing_08@126.com]
 */
public class NewsPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {

        News news = resultItems.get("news");
        if (news != null) {
            System.out.println("********************************************************");
            System.out.println(news);
            System.out.println("===============================================================================");
        }
    }
}
