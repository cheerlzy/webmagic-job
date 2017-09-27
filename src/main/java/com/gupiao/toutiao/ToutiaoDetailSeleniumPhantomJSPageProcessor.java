package com.gupiao.toutiao;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author zhuxing [zhuxing1@xiaoniu66.com, xing_08@126.com]
 */
public class ToutiaoDetailSeleniumPhantomJSPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        //System.out.println(page.getHtml());
        System.out.println("==============================================");
        System.out.println(page.getHtml().xpath("//div[@class='article-box']"));
        System.out.println("==============================================");
        System.out.println(page.getHtml().xpath("//ul[@class='tag-list']"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        System.getProperties().setProperty("selenuim_config", "C:\\zhuxing\\selenium\\config.ini");
        Spider.create(new ToutiaoDetailSeleniumPhantomJSPageProcessor()).addUrl("https://www.toutiao.com/a6457033079058858509/").setDownloader(new SeleniumDownloader()).thread(5).run();
    }
}
