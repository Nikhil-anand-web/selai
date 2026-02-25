package com.ai.selai.pages;

import org.openqa.selenium.*;
import org.springframework.stereotype.Component;

/**
 * Naukri Home page
 * - Search input + Search action
 * - Cookie banner dismiss (best-effort)
 * - Slow, smooth scroll until a target element is visible
 * - Empty search prevention check (disabled OR validation OR no navigation)
 */
@Component
public class HomePage extends  BasePage {



    By loginButton = By.id("login_Layer");
    By usenameField = new By.ByXPath("//div[@class=\"form-row\"]/input[@type='text']");
    By passwordField = new By.ByXPath("//div[@class=\"form-row\"]/input[@type='password']");
    By loginSubmit = By.cssSelector(".btn-primary.loginButton");
    public void login(String userName, String password){
        System.out.println(userName+" "+password);


        waitAndClick(loginButton);



        waitAndSendKeys(this.usenameField ,userName);
        waitAndSendKeys(this.passwordField ,password);


         waitAndClick(this.loginSubmit);





    }

    public String getPasswordFieldType(){

        waitAndClick(this.loginButton);
        return waitAndGetElement(passwordField).getAttribute("type");

    }
    // Locators (as per your inspections)
    private final By searchInput  = By.cssSelector("input.suggestor-input");
    private final By searchButton = By.cssSelector("div.qsbSubmit");

    // Generic validation containers (site can vary)
    private final By validationCandidates = By.cssSelector(
            "[role='tooltip'], .error, .err, .validation, [data-error], .snackbar, .toast");

    // Cookie banner patterns (best-effort)
    private final By cookieBar = By.xpath("//*[contains(translate(normalize-space(.),'COOKIE','cookie'),'cookie')][self::div or self::section]");
    private final By cookieAcceptBtn =
            By.xpath("//button[contains(translate(normalize-space(.),'GOTIT','got it'),'got it')]" +
                    " | //button[contains(translate(normalize-space(.),'ACCEPT','accept'),'accept')]" +
                    " | //a[contains(translate(normalize-space(.),'GOTIT','got it'),'got it')]" +
                    " | //a[contains(translate(normalize-space(.),'ACCEPT','accept'),'accept')]");

    public HomePage(WebDriver wd) {
        super(wd);

    }

    public void waitForReady() {


        waitUntilVisiblityOf(searchButton);
        waitUntilVisiblityOf(searchInput);

        dismissCookieBannerIfPresent();

    }

    /** Try to close cookie banner (best-effort, safe if not present). */
    public void dismissCookieBannerIfPresent() {
        try {
            if (!wd.findElements(cookieBar).isEmpty() && !wd.findElements(cookieAcceptBtn).isEmpty()) {
                WebElement btn = wd.findElement(cookieAcceptBtn);
                ((JavascriptExecutor) wd).executeScript("arguments[0].click();", btn);

                waitUntilinvisibilityOf(btn);
            }
        } catch (Exception ignored) {}
    }

    public void clearSearch() {

        WebElement inp = waitAndGetElement(searchInput);
        inp.sendKeys(Keys.chord(Keys.CONTROL, "a"));
       inp.sendKeys(Keys.DELETE);




    }

    public void typeSearch(String text) {

        waitAndSendKeys(searchInput,text);

    }

    public void clickSearch() {

        waitAndClick(searchButton);
    }

    /**
     * 🚶‍♂️ Slow, smooth scrolling until the element becomes visible, then center it.
     * Visually shows the footer/LinkedIn icon coming into view.
     */
    public void scrollUntilVisible(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) wd;

        int step = 120;          // small step for slow movement
        int maxSteps = 40;       // up to ~40 steps
        long pauseMs = 300;      // delay between steps so user can see movement

        for (int i = 0; i < maxSteps; i++) {
            try {
                WebElement el = wd.findElement(locator);

                if (el.isDisplayed()) {
                    // Final smooth alignment into center
                    js.executeScript("arguments[0].scrollIntoView({block:'center', behavior:'smooth'});", el);
                    pause(700);
                    return;
                }

            } catch (NoSuchElementException ignored) {}

            // Gentle downward motion
            js.executeScript("window.scrollBy(0, arguments[0]);", step);
            pause(pauseMs);
        }

        // If still not visible, wait and then do a final smooth center
//        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement el = waitAndGetElement(locator);

        js.executeScript("arguments[0].scrollIntoView({block:'center', behavior:'smooth'});", el);
        pause(700);
    }

    /**
     * Returns true if empty search is prevented:
     *  - by disabled state OR validation UI OR no navigation after click.
     */
    public boolean isEmptySearchPrevented() {
        clearSearch();
        String urlBefore = wd.getCurrentUrl();
        String titleBefore = wd.getTitle();

        WebElement btn = waitAndGetElement(searchButton);
        boolean disabledAttr = "true".equalsIgnoreCase(btn.getAttribute("aria-disabled"))
                || btn.getAttribute("disabled") != null;

        // Try click anyway (it's a DIV on Naukri)
        try { btn.click(); } catch (Exception ignored) {}

        boolean validationShown;
        try {
            validationShown = getWait().until(d -> !d.findElements(validationCandidates).isEmpty());
        } catch (TimeoutException te) {
            validationShown = false;
        }

        boolean navigated = !(urlBefore.equals(wd.getCurrentUrl()) && titleBefore.equals(wd.getTitle()));
        return disabledAttr || validationShown || !navigated;
    }

    // ---------- small utility ----------

    /** Safe sleep wrapper that restores interrupt status if interrupted. */
    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}