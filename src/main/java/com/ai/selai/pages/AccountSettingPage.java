package com.ai.selai.pages;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class AccountSettingPage extends BasePage {

    private By blockCompany = By.xpath("//li[@id='BlockCompany']");
    private By blockingInput = By.xpath("//div[@class='sWrap']/div[@class='inpWrap']/input");
    private By saveButton = By.id("saveSettingBtn");
    private By chips = By.xpath("//span[@class='tagTxt']");
    private By dropDown = By.xpath(" //div[@id='sugDrp_blockCompanySugg']/ul/li");

public AccountSettingPage(WebDriver wd){

    super(wd);

}


    public void navigateToBlocking(){


      waitAndClick(blockCompany,3);








    }
    public void setBlockCompany(String comp) {


        // We use a loop to retry if the element goes stale immediately
        for (int i = 0; i < 3; i++) {
            try {
                // Re-identify the element inside the loop
                WebElement inp = waitAndGetElement(blockingInput,10);
                inp.clear();
                inp.sendKeys(comp);

                // If sendKeys succeeds, break the loop
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Element went stale, retrying... attempt " + (i + 1));
            }
        }



        waitAndClick(dropDown);

        waitAndClick(saveButton);
    }

    public List<String> getListOfBlockCompanies(){
        waitAndGetElement(chips);
        List<WebElement> chipss = wd.findElements(chips);
        return chipss.stream().map(WebElement::getText).collect(Collectors.toList());
    }





}
