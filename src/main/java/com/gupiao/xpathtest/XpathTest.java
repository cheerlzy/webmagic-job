package com.gupiao.xpathtest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import us.codecraft.webmagic.selector.Selectors;
import us.codecraft.webmagic.selector.XpathSelector;

/**
 * @author zhuxing [zhuxing1@xiaoniu66.com, xing_08@126.com]
 */
public class XpathTest {

    public static void main(String[] args) {
        /*
        String html = "<li class=\"essay-item\">  <div class=\"media-info\"> <a class=\"img-wrap\" target=\"_blank\" href=\"/user/6253216165\"> <img alt=\"\" src=\"http://p3.pstatp.com/thumb/2c6e001766dbff17400e\"> </a> <a class=\"media-name\" target=\"_blank\" href=\"/user/6253216165\">一个乌龟爱好者</a> </div> <a target=\"_blank\" href=\"/api/article/joke/a67131007092\"> <p class=\"essay-content\">各手机品牌的粉丝名称 小米叫米粉，华为叫花粉，苹果叫果粉，魅族叫煤油，一加叫加油，锤子叫锤友… 那么问题来了，三星的粉丝叫什么？\uD83D\uDE04</p> </a>  <div class=\"essay-tool\"> <div data-type=\"dig\" class=\"action-btn action-btn-left \"> <i class=\"y-icon icon-handup\"></i><span>302</span> </div> <div data-type=\"bury\" class=\"action-btn action-btn-left \"> <i class=\"y-icon icon-handdown\"></i><span>29</span> </div> <div class=\"action-btn action-btn-right share-wrap\"> <i class=\"y-icon icon-share\"></i> <span>11</span> <div class=\"snsbox clearfix\"> <p class=\"snsbox-share\">分享到:</p> <div class=\"snszone\"> <i class=\"sns-btn sns-weixin\" title=\"分享到微信\" data-share=\"0\"> <div class=\"qrcodeW\"> <div class=\"qrcode\"></div> </div> </i> <i class=\"sns-btn sns-qzone\" title=\"分享到QQ空间\"></i> <i class=\"sns-btn sns-weibo\" title=\"分享到新浪微博\"></i> </div> </div> </div> <div class=\"action-btn action-btn-right \"> <i class=\"y-icon icon-favorite\"></i> <span>11</span> </div> <a target=\"_blank\" class=\"action-btn action-btn-right essay-comment\" href=\"/api/article/joke/a67131007092#comment\"> <i class=\"y-icon icon-comment\"></i><span>0</span> </a> </div> </li>";
        Element essayElement = Jsoup.parse(html);

        XpathSelector mediaNameSelector = Selectors.xpath("//a[@class='media-name']/text()");
        System.out.println(mediaNameSelector.select(essayElement));

        XpathSelector contentSelector = Selectors.xpath("//p[@class='essay-content']/text()");
        System.out.println(contentSelector.select(essayElement));


        XpathSelector digSelector = Selectors.xpath("//div[@data-type='dig']//span/text()");
        System.out.println(digSelector.select(essayElement));

        XpathSelector burySelector = Selectors.xpath("//div[@data-type='bury']//span/text()");
        System.out.println(burySelector.select(essayElement));

        //XpathSelector favoriteSelector = Selectors.xpath("//i[@class='icon-favorite']/..");
        //System.out.println(favoriteSelector.select(essayElement));
        */

        String html = "<div class=\"mark\" id=\"videokg\" bosszone=\"keyword\">\n" +
            "<span><a target=\"_blank\" href=\"http://finance.qq.com/dc_2016/mcontent/tagsList.htm?tags=%E4%BA%8C%E6%89%8B%E6%88%BF\">二手房</a></span><span><a target=\"_blank\" href=\"http://finance.qq.com/dc_2016/mcontent/tagsList.htm?tags=%E6%A5%BC%E5%B8%82%E8%B0%83%E6%8E%A7\">楼市调控</a></span><span><a target=\"_blank\" href=\"http://finance.qq.com/dc_2016/mcontent/tagsList.htm?tags=%E6%88%90%E4%BA%A4%E9%87%8F\">成交量</a></span>\n" +
            "</div>" ;
        Element essayElement = Jsoup.parse(html);
        XpathSelector markSelector = Selectors.xpath("//a/text()");
        System.out.println(markSelector.selectList(essayElement));

    }

}
