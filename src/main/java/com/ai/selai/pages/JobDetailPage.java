package com.ai.selai.pages;

import org.openqa.selenium.*;
import org.springframework.stereotype.Component;

@Component
/** Job detail page – verify the company logo image renders for real. */
public class JobDetailPage extends BasePage {




    private final By logoPrimary  = By.cssSelector("img[class*='logo']");
    private final By logoAltText  = By.xpath("//img[contains(translate(@alt,'LOGO','logo'),'logo')]");
    private final By headerScoped = By.xpath("(//header|//div[contains(@class,'job') or contains(@id,'job')])[1]//img");

    public JobDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCompanyLogoRendered() {
        WebElement img;
        try {
            img = waitAndGetElement(logoPrimary);
        } catch (TimeoutException te1) {
            try {
                img = waitAndGetElement(logoAltText);

            } catch (TimeoutException te2) {
                img = waitAndGetElement(headerScoped);
            }
        }

        if (!img.isDisplayed()) return false;

        Object result = ((JavascriptExecutor) wd).executeScript(
                "var i=arguments[0]; if(!i) return false;" +
                        "var w=(i.naturalWidth||0), h=(i.naturalHeight||0);" +
                        "var r=i.getBoundingClientRect();" +
                        "return (w>0 && h>0 && r.width>0 && r.height>0);", img);

        return (result instanceof Boolean) && ((Boolean) result);
    }
}