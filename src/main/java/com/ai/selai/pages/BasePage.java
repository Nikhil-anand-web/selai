package com.ai.selai.pages;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class BasePage {
    public final WebDriver wd ;
     private  final int defaultTiming =10;


    public  void waitAndEscape(By xpa,int second){

         try{
             WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(second));
             wait.until(ExpectedConditions.elementToBeClickable(xpa));
             Actions action = new Actions(wd);
             action.sendKeys(Keys.ESCAPE).build().perform();

         }catch (Exception e){
             System.out.println("warning :" +"modal not detected");
         }

    }
    public  void waitAndEscape(By xpa){

        try{
            WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(defaultTiming));
            wait.until(ExpectedConditions.elementToBeClickable(xpa));
            Actions action = new Actions(wd);
            action.sendKeys(Keys.ESCAPE).build().perform();

        }catch (Exception e){
            System.out.println("warning :" +"modal not detected");
        }

    }

    public void waitAndClick(By el ){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(defaultTiming));
        wait.until(ExpectedConditions.elementToBeClickable(el)).click();



    }
    public void waitUntilVisiblityOf(By el ){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(defaultTiming));
        wait.until(ExpectedConditions.visibilityOfElementLocated(el));



    }
    public void waitUntilinvisibilityOf(WebElement el){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(defaultTiming));
        wait.until(ExpectedConditions.invisibilityOf(el));

    }
    public void waitUntilinvisibilityOf(WebElement el,int seconds){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.invisibilityOf(el));

    }
    public void waitUntilVisiblityOf(By el , int second ){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.visibilityOfElementLocated(el));



    }
    public WebElement waitAndGetElement(By el ){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(defaultTiming));
        return wait.until(ExpectedConditions.elementToBeClickable(el));

    }

    public void waitAndSendKeys(By el ,String key){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(defaultTiming));
       WebElement els= wait.until(ExpectedConditions.elementToBeClickable(el));
       els.clear();
       els.sendKeys(key);

    }

    public WebDriverWait getWait(){
         return new WebDriverWait(wd,Duration.ofSeconds(defaultTiming));
    }
    public WebDriverWait getWait(int seconds){
        return new WebDriverWait(wd,Duration.ofSeconds(seconds));
    }


    public String waitAndGetText(By el ){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(defaultTiming));
        return wait.until(ExpectedConditions.elementToBeClickable(el)).getText();

    }
    public void waitAndClick(By el , int seconds){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(el)).click();



    }
    public WebElement waitAndGetElement(By el , int seconds){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(seconds));
       return wait.until(ExpectedConditions.elementToBeClickable(el));

    }
    public void waitAndSendKeys(By el , int seconds,String key){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(el)).sendKeys(key);

    }

    public String waitAndGetText(By el , int seconds){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.elementToBeClickable(el)).getText();

    }



}
