package com.ai.selai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class FaqPage extends  BasePage {

    private By searchInp  = By.id("keyword_search");
    private By sugList = By.xpath("//div[@class='eac-item']");
    private By result = By.xpath("//div[@class='registration_area']//div[@class='heading']/h1");
    public FaqPage(WebDriver wd){

        super(wd);


    }

    public String search(String key){

       waitAndSendKeys(searchInp,key);
        WebElement sugel=waitAndGetElement(sugList);


        String text = sugel.getText();
        sugel.click();
        return text;

    }

    public String getSearchResultHeadlineText(){


       return waitAndGetText(result);
    }

}
