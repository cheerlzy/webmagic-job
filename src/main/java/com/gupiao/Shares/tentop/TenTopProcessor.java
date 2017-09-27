package com.gupiao.Shares.tentop;

import com.gupiao.oanews.NewsPipeline;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.XpathSelector;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xn066751 on 2017/9/15.
 */
public class TenTopProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    @Override
    public void process(Page page) {
        XpathSelector xpathSelector= new XpathSelector("table[@class='m-table1']/tbody/tr");
        List<Element> list2=xpathSelector.selectElements(page.getRawText());
        Elements elements=new Elements();
        List<TopTenEntity> list=new ArrayList();
        for(Element element:list2){
            TopTenEntity topTenEntity=new TopTenEntity();
            elements= element.getAllElements();
            List<String> listEelement=elements.eachText();
            String[] text=listEelement.get(0).split(" ");
            topTenEntity.setRank(text[0]);
            topTenEntity.setCode(text[1]);
            topTenEntity.setName(text[2]);
            topTenEntity.setSettlement(text[3]);
            topTenEntity.setHighDownRange(text[4]);
            topTenEntity.setHighDownAmount(text[5]);
            topTenEntity.setBugAmount(text[6]);
            topTenEntity.setSellAmount(text[7]);
            topTenEntity.setCleanAmount(text[8]);
            topTenEntity.setDealAmount(text[9]);
            list.add(topTenEntity);
        }
        page.putField("data",list);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        NewsPipeline newsPipeline = new NewsPipeline();
        Spider.create(new TenTopProcessor())
                .addPipeline(new TopPipeline())
                .addUrl("http://data.10jqka.com.cn/hgt/ggtb/")
                .thread(1).run();

    }



}
