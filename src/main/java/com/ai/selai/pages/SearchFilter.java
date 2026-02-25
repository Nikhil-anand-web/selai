
package com.ai.selai.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.springframework.stereotype.Component;

@Component
public class SearchFilter extends BasePage {



    public SearchFilter(WebDriver wd) {
        
        
        super(wd);
    }

    By searchButton = By.xpath("//*[@id=\"root\"]/div[7]/div/div[1]/div[6]");
    By locationFilter = By.xpath("//label[@for='chk-Bengaluru-cityTypeGid-']//i[@class='ni-icon-unchecked']");
    By jobHeader = By.xpath("");
    By salaryFilter = By.xpath("//*[@id=\"search-result-container\"]/div[1]/div[1]/div/div/div[2]/div[5]/div[2]/div[3]/label/i");
    By errorMessage = By.xpath("//*[@id=\"search-result-container\"]/div[1]/div[1]/div[1]/div[1]");





    public void clickSearch() {
        wd.findElement(searchButton).click();
    }

    public void clickLocationFilter() {
        wd.findElement(locationFilter).click();
    }

    public void clickSalaryFilter() {
        wd.findElement(salaryFilter).click();
    }

    // Getter Methods for Assertions
//    public String getJobHeaderText() {
//        return wd.findElement(jobHeader).getText();
//    }

    public String getErrorMessage() {
        return wd.findElement(errorMessage).getText();
    }
}








