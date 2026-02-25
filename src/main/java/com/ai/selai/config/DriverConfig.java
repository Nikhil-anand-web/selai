package com.ai.selai.config;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverConfig {
    @Bean
    public WebDriver getDriver(){
      WebDriver wd =  new ChromeDriver();
      wd.get("https://www.naukri.com/");
        return wd;
    }
}
