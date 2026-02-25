package com.ai.selai.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
/** Footer component – LinkedIn link handling. */
public class FooterSection extends BasePage {



    // Public so test can scroll to these before clicking
    public final By linkedInExact = By.cssSelector("footer a[href*='linkedin.com/company/naukri.com']");
    public final By linkedInAny   = By.cssSelector("footer a[href*='linkedin.com']");

    public FooterSection(WebDriver dr) {
        super(dr);
    }


    /** Wait for visibility (not just clickable) and click the LinkedIn link. */
    public void clickLinkedInVisible() {
        try {

            waitAndClick(linkedInExact);
        } catch (TimeoutException te) {

            waitAndClick(linkedInAny);
        }
    }
}