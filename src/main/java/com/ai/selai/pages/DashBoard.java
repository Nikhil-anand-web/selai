package com.ai.selai.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;


@Component
public class DashBoard extends BasePage {
    private By burgerButton = By.cssSelector(".nI-gNb-drawer");
    private By accountSetting = By.xpath("//a[contains(text(),'Settings')]");
    private By logoutB = By.xpath("//a[contains(text(),'Logout')]");
    private By faq = By.xpath("//a[contains(text(),'FAQs')]");
   private By profileSetting = By.className("nI-gNb-info__sub-link");

    public DashBoard(WebDriver dr) {
        super(dr);
    }


    public void navigateToAccountSetting(){


        waitAndClick(this.burgerButton);
        waitAndClick(accountSetting);

    }

    public void navigateToSetting(){

        waitAndClick(burgerButton);


        waitAndClick(profileSetting);



    }
    public void logout(){

     waitAndClick(burgerButton);


      waitAndClick(logoutB);



    }

    public void navigateToFaq() {
        waitAndClick(burgerButton);
        waitAndClick(faq,9);



    }
}
