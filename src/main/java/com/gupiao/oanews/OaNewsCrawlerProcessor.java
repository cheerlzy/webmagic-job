package com.gupiao.oanews;

import com.alibaba.fastjson.JSON;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxing [zhuxing1@xiaoniu66.com, xing_08@126.com]
 */
public class OaNewsCrawlerProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).addCookie("JSESSIONID", "27B4AAB5465A51769F1912FC8545E6A1").addCookie("LtpaToken", "AAECAzU5OUUzNTIxNTk5RURERTF4bjA2NTEwOWYUiHiMpKd/NyTXPg329oHcpcUG");

    @Override
    public void process(Page page) {

        if (page.getRequest().getExtra("news") == null) {
            List<String> jsonNews = new JsonPathSelector("$.datas[*]").selectList(page.getRawText());
            List<News> news = new ArrayList<>(jsonNews.size());
            for (String json : jsonNews) {
                List<NewsProperty> newsProperties = JSON.parseArray(json, NewsProperty.class);
                News news1 = buildNews(newsProperties);
                Request request = new Request(news1.getUrl());
                request.putExtra("news", news1);
                page.addTargetRequest(request);
            }
        } else {
            News news = (News) page.getRequest().getExtra("news");

            String content = page.getHtml().xpath("//div[@name='rtf_docContent']").toString();
            news.setContent(content);
            page.putField("news", news);
        }

    }

    private News buildNews(List<NewsProperty> newsProperties) {
        News news = new News();
        for (NewsProperty newsProperty : newsProperties) {
            if (newsProperty.getCol().equalsIgnoreCase("fdId")) {
                news.setId(newsProperty.getValue());
            } else if (newsProperty.getCol().equalsIgnoreCase("url")) {
                news.setUrl("http://oa.xiaoniu66.com/" + newsProperty.getValue());
            } else if (newsProperty.getCol().equalsIgnoreCase("docSubject")) {
                news.setTitle(newsProperty.getValue().replaceAll("</?[^>]+>", ""));
            } else if (newsProperty.getCol().equalsIgnoreCase("fdImportance")) {
                news.setImportance(newsProperty.getValue());
            } else if (newsProperty.getCol().equalsIgnoreCase("docCreateTime")) {
                news.setCreateTime(newsProperty.getValue());
            } else if (newsProperty.getCol().equalsIgnoreCase("docHits")) {
                news.setHits(Integer.valueOf(newsProperty.getValue().replaceAll("</?[^>]+>", "").trim()));
            }
        }
        return news;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        NewsPipeline newsPipeline = new NewsPipeline();
        Spider.create(new OaNewsCrawlerProcessor())
            .addPipeline(newsPipeline)
            .addUrl("http://oa.xiaoniu66.com/sys/news/sys_news_main/sysNewsMainIndex.do?method=listChildren&q.docStatus=30&pageno=1&rowsize=15&orderby=fdIsTop%3BfdTopTime%3BdocAlterTime&ordertype=down")
            .thread(1).run();

    }

    public static class NewsProperty {

        private String col;
        private String value;

        public String getCol() {
            return col;
        }

        public void setCol(String col) {
            this.col = col;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class News {
        private String id;
        private String url;
        private String title;
        private String importance;
        private String createTime;
        private int hits;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImportance() {
            return importance;
        }

        public void setImportance(String importance) {
            this.importance = importance;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "News{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", importance='" + importance + '\'' +
                ", createTime='" + createTime + '\'' +
                ", hits=" + hits +
                ", content='" + content + '\'' +
                '}';
        }
    }
}
