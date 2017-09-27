package com.gupiao.devcloud;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.XpathSelector;

import java.util.List;
import java.util.Set;

/**
 * @author zhuxing [zhuxing1@xiaoniu66.com, xing_08@126.com]
 */
public class ProjectCrawlerProcessor implements PageProcessor {

    private static Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        System.out.println("==================================");
        System.out.println(page.getRawText());
        List<String> projectNames = new XpathSelector("//div[@class='project-name']").selectList(page.getRawText());
        System.out.println(projectNames);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Set<Cookie> cookies = getCookies();
        setCookies(cookies);

        System.getProperties().setProperty("selenuim_config", "C:\\zhuxing\\selenium\\config.ini");
        Spider.create(new ProjectCrawlerProcessor())
            .addUrl("http://devcloud.niudingfeng.com/projects")
            .setDownloader(new SeleniumDownloader())
            .thread(5).run();
    }

    private static void setCookies(Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            site.addCookie(cookie.getName(), cookie.getValue());
        }
    }

    private static Set<Cookie> getCookies() {
        System.getProperties().setProperty("phantomjs.binary.path", "C:\\zhuxing\\selenium\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        WebDriver driver =  new PhantomJSDriver();
        driver.get("http://devcloud.niudingfeng.com/login");

        driver.findElement(By.id("j_username")).clear();
        //在******中填你的用户名
        driver.findElement(By.id("j_username")).sendKeys("xn065109");

        driver.findElement(By.id("j_password")).clear();
        //在*******填你密码
        driver.findElement(By.id("j_password")).sendKeys("zhxuingXn!045612");

        //模拟点击登录按钮
        WebElement ele = driver.findElement(By.xpath("//button[@type='submit']"));
        ele.click();

        //获取cookie信息
        Set<Cookie> cookies = driver.manage().getCookies();
        driver.close();
        return cookies;
    }
}
