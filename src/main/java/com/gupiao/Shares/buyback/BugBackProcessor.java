package com.gupiao.Shares.buyback;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.XpathSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xn066751 on 2017/9/15.
 */
public class BugBackProcessor implements PageProcessor{
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    private static int pageSize=0;
    private  BugBackSecondProcessor bugBackSecondProcessor=new BugBackSecondProcessor();
    @Override
    public void process(Page page) {
        if(pageSize==0) {
            XpathSelector pageText = new XpathSelector("div[@id='pageNoDiv']/a[6]/text()");
            pageSize = Integer.valueOf(pageText.select(page.getRawText()));
        }
        XpathSelector xpathSelector= new XpathSelector("div[@class='data clearfix']/");
        List<Element> list2=xpathSelector.selectElements(page.getRawText());
        Elements elements=new Elements();
        List<BugBackEntiy> list=new ArrayList();
        for(Element element:list2) {
            BugBackEntiy bugBackEntiy = new BugBackEntiy();
            elements = element.getAllElements();
            List<String> listEelement = elements.eachText();
            String[] text = listEelement.get(0).split(" ");
            bugBackEntiy.setCode(text[1]);
            bugBackEntiy.setName(text[2]);
            bugBackEntiy.setAmount(text[3]);
            bugBackEntiy.setHighPrice(text[4]);
            bugBackEntiy.setLowPrice(text[5]);
            bugBackEntiy.setAvePrive(text[6]);
            bugBackEntiy.setTotalAmount(text[7]);
            bugBackEntiy.setDate(text[8]);
            list.add(bugBackEntiy);
        }
        page.putField("data",list);
        bugBackSecondProcessor.run(pageSize);
    }
    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Spider.create(new BugBackProcessor())
                .addPipeline(new BugBackPipeline())
                .addUrl("http://hk.eastmoney.com/buyback.html?code=&sdate=&edate=")
                .thread(1).run();

    }
}
