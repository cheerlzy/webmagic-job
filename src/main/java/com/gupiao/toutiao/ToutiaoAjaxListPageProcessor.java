package com.gupiao.toutiao;

import org.apache.commons.collections.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.List;

/**
 * @author zhuxing [zhuxing1@xiaoniu66.com, xing_08@126.com]
 */
public class ToutiaoAjaxListPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {

        List<String> titles = new JsonPathSelector("$.data[*].title").selectList(page.getRawText());
        if (CollectionUtils.isNotEmpty(titles)) {
            int i = 0;
            for (String title : titles) {
                System.out.println("标题i="+(++i)+"   :   "+title);
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //Spider.create(new ToutiaoAjaxListPageProcessor()).addUrl("https://www.toutiao.com/api/pc/feed/?category=news_tech&utm_source=toutiao&widen=3").thread(5).run();


        Spider.create(new ToutiaoAjaxListPageProcessor()).addUrl("https://www.toutiao.com/api/pc/feed/?category=news_tech&utm_source=toutiao&widen=1&max_behot_time=1503390134&max_behot_time_tmp=1503390134&tadrequire=true&as=A115B9C91BAF44B&cp=599B5F44C4FB9E1").thread(5).run();

    }
}
