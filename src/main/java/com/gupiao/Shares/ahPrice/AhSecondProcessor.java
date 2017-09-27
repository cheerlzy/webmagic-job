package com.gupiao.Shares.ahPrice;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xn066751 on 2017/9/15.
 */
public class AhSecondProcessor implements PageProcessor{
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    @Override
    public void process(Page page) {
        String text=page.getRawText().substring(page.getRawText().indexOf("{"),page.getRawText().lastIndexOf("}")+1);
        List<String> textList = new JsonPathSelector("$.rank[*]").selectList(text);
        List<AhEntity> list=new ArrayList<>();
        for (String text2:textList){
            AhEntity ahEntity=new AhEntity();
            String[] entity=text2.split(",");
            ahEntity.setName(entity[2]);
            ahEntity.setCode(entity[1]);
            ahEntity.setLatestPrice(entity[3]);
            ahEntity.setHighLowRange(entity[4]);
            ahEntity.setHkgu("�۹ɰ�");
            ahEntity.setACode(entity[5]);
            ahEntity.setLatestPrice2(entity[8]);
            ahEntity.setHighLowRange2(entity[9]);
            ahEntity.setAGu("A�ɰ�");
            ahEntity.setComPrice(entity[13]);
            ahEntity.setOverFlowPrice(entity[15]);
            list.add(ahEntity);
        }
        page.putField("data",list);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void run(int pageSize){
        if(pageSize>=2) {
            for (int i = 2; i <= pageSize; i++) {
                Spider.create(new AhSecondProcessor())
                        .addPipeline(new AhPipeline())
                        .addUrl("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?" +
                                "type=CT&cmd=C._AHH&sty=FCABHL&sortType=C&sortRule=-1&page="+i+"&pageSize=20&js=var%20quote_123" +
                                "%3d%7Brank:[(x)],pages:(pc)%7D&token=44c9d251add88e27b65ed86506f6e5da&jsName=quote_123&_g=0.7338211218271915")
                        .thread(1).run();
            }
        }
    }
}
