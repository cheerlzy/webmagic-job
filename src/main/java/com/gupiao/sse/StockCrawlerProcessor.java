package com.gupiao.sse;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author zhuxing [zhuxing1@xiaoniu66.com, xing_08@126.com]
 */
public class StockCrawlerProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        System.out.println(page.getHtml());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        System.getProperties().setProperty("selenuim_config", "C:\\zhuxing\\selenium\\config.ini");
        Spider.create(new StockCrawlerProcessor()).addUrl("http://www.sse.com.cn/assortment/stock/list/share/").setDownloader(new SeleniumDownloader()).thread(5).run();
    }

}
