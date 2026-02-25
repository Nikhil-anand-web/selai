package com.ai.selai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class SettingPage extends BasePage {
    private By editButton = By.xpath("//div[@class=\"widgetHead\"]/span[@class=\"edit icon\"]");
    private By textar = By.id("resumeHeadlineTxt");

    private By save = By.xpath("//div[@class=\"action s12\"]/button");
    private By displayOfHeader = By.xpath("//textarea[@id ='resumeHeadlineTxt']");
    private By skillChips = By.xpath("//div[@class='prefill']/span[@class='chip typ-14Medium']");
    private By editButtonForSkill = By.xpath("//div[@id='lazyKeySkills']//span[@class='edit icon']");
    private By skillDropdown = By.xpath("//div[@class='suggest keySkillSuggCont']/div[@class='sugCont slideDown']/ul/li");
    private By skillSugInput = By.id("keySkillSugg");
    private By saveButtonOfSkill = By.id("saveKeySkills");
    private By modalWhileSaving = By.xpath("//div[@class='ltCont']/div/div[@class='crossLayer']/span[@class='icon' and text() = 'CrossLayer']");

    public SettingPage(WebDriver wd){
        super(wd);

    }
    public void editHeadLine(String header){



        waitAndClick(this.editButton,3);


        waitAndSendKeys(this.textar,3,header);
        waitAndClick(this.save);





    }
    public String getHeadLine(){


        return waitAndGetText(this.displayOfHeader);

    }
    public void insertSkill(String inp){
        waitAndEscape(modalWhileSaving);
        waitAndClick(this.editButtonForSkill,7);
        waitAndSendKeys(this.skillSugInput,7,inp);
        waitAndClick(this.skillDropdown);

        waitAndClick(this.saveButtonOfSkill);





    }

    public List<String> getListOfSkillChip(){

        List<WebElement> chips = wd.findElements(this.skillChips);

        return chips.stream().map(WebElement::getText).collect(Collectors.toList());


    }

}
