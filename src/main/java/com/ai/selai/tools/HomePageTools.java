package com.ai.selai.tools;

import com.ai.selai.dto.ToolResult;
import com.ai.selai.pages.HomePage;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HomePageTools {

    private final HomePage homePage;

    // ---------------- AUTH ----------------
    @Tool(description = "Returns the current page URL and title")
    public String getCurrentPageInfo() {
        try {
            return "URL=" + homePage.wd.getCurrentUrl() + ", TITLE=" +  homePage.wd.getTitle();
        } catch (Exception e) {
            return "Unable to read browser state";
        }
    }
    @Tool(description = "Logs into the application using username and password Homepage")
    public ToolResult login(
            @ToolParam(description = "Registered username or email")
            String username,

            @ToolParam(description = "Account password")
            String password
    ) {
        try {
            homePage.login(username, password);
            return new ToolResult(true, "Login submitted successfully");
        } catch (Exception e) {
            return new ToolResult(false, "Login failed: " + rootCause(e));
        }
    }

    @Tool(description = "Checks whether the password input field is masked")
    public Object isPasswordMasked() {
        try {
            String type = homePage.getPasswordFieldType();
            boolean masked = "password".equalsIgnoreCase(type);
            return masked;
        } catch (Exception e) {
            return new ToolResult(false, "Failed to determine password field type");
        }
    }

    // ---------------- SEARCH ----------------

    @Tool(description = "Prepares the home page for interaction by waiting for search UI and dismissing cookies")
    public ToolResult waitForHomePageReady() {
        try {
            homePage.waitForReady();
            return new ToolResult(true, "Home page is ready");
        } catch (Exception e) {
            return new ToolResult(false, "Home page did not become ready: " + rootCause(e));
        }
    }

    @Tool(description = "Clears the search input field")
    public ToolResult clearSearch() {
        try {
            homePage.clearSearch();
            return new ToolResult(true, "Search field cleared");
        } catch (Exception e) {
            return new ToolResult(false, "Failed to clear search field");
        }
    }

    @Tool(description = "Types a search query into the search field")
    public ToolResult typeSearch(
            @ToolParam(description = "Search text, e.g. 'Java Developer'")
            String query
    ) {
        if (query == null || query.isBlank()) {
            return new ToolResult(false, "Search query must not be empty");
        }

        try {
            homePage.typeSearch(query);
            return new ToolResult(true, "Search text entered");
        } catch (Exception e) {
            return new ToolResult(false, "Failed to type search text");
        }
    }

    @Tool(description = "Clicks the search button")
    public ToolResult submitSearch() {
        try {
            homePage.clickSearch();
            return new ToolResult(true, "Search submitted");
        } catch (Exception e) {
            return new ToolResult(false, "Failed to submit search");
        }
    }

    // ---------------- VALIDATION ----------------

    @Tool(description = "Checks whether submitting an empty search is prevented by the application")
    public Object isEmptySearchPrevented() {
        try {
            boolean prevented = homePage.isEmptySearchPrevented();
            return prevented;
        } catch (Exception e) {
            return new ToolResult(false, "Failed to evaluate empty search prevention");
        }
    }

    // ---------------- UTILS ----------------

    private String rootCause(Throwable e) {
        Throwable cause = e;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause.getClass().getSimpleName();
    }
}