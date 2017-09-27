package com.gupiao.Shares.newLow;

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
public class NewLowProcessor implements PageProcessor{
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    @Override
    public void process(Page page) {
        XpathSelector xpathSelector= new XpathSelector("table[@class='tblM s2']/tbody/tr");
        List<Element> list2=xpathSelector.selectElements(page.getRawText());
        Elements elements=new Elements();
        List<NewLowEntity> list=new ArrayList();
        for(Element element:list2) {
            NewLowEntity newLowEntity = new NewLowEntity();
            elements = element.getAllElements();
            List<String> listEelement = elements.eachText();
            String[] text = listEelement.get(0).split(" ");
            newLowEntity.setName(text[0]);
            newLowEntity.setAh(text[1]);
            newLowEntity.setPrice(text[2]);
            newLowEntity.setRiseFall(text[3]);
            newLowEntity.setTodayLow(text[4]);
            newLowEntity.setWeekLow(text[5]);
            newLowEntity.setDealAmount(text[6]);
            newLowEntity.setDealMoney(text[7]);
            newLowEntity.setCityRatio(text[8]);
            newLowEntity.setCityDebtRatio(text[9]);
            newLowEntity.setInComeRatio(text[10]);
            newLowEntity.setMarketValue(text[11]);
            list.add(newLowEntity);
        }
        page.putField("data",list);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new NewLowProcessor())
                .addPipeline(new NewLowPipeline())
                .addUrl("http://www.aastocks.com/sc/stocks/market/top-rank/stock?type=K&t=1&s=&o=1&p=")
                .thread(1).run();

    }
}
