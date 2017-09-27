package com.gupiao.devcloud;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.Set;

/**
 * @author zhuxing [zhuxing1@xiaoniu66.com, xing_08@126.com]
 */
public class LoginTest {

    public static void main(String[] args) {
        //System.getProperties().setProperty("selenuim_config", "C:\\zhuxing\\selenium\\config.ini");
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

        System.out.println(cookies);
    }
}
