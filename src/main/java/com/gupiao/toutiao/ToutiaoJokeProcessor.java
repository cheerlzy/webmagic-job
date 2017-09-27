package com.gupiao.toutiao;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author zhuxing [zhuxing1@xiaoniu66.com, xing_08@126.com]
 */
public class ToutiaoJokeProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {

        List<String> jokes = page.getHtml().xpath("//li[@class='essay-item']").all();
        for (String joke : jokes) {
            System.out.println("===========================================");
            System.out.println(joke);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        System.getProperties().setProperty("selenuim_config", "C:\\zhuxing\\selenium\\config.ini");
        Spider.create(new ToutiaoJokeProcessor()).addUrl("https://www.toutiao.com/ch/essay_joke/").setDownloader(new SeleniumDownloader()).thread(5).run();
    }

}
