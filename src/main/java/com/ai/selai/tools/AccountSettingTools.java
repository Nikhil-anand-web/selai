package com.ai.selai.tools;

import com.ai.selai.dto.ToolResult;
import com.ai.selai.pages.AccountSettingPage;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class AccountSettingTools {

    private final AccountSettingPage accountSettingPage;

    @Tool(description = "Opens the Block Company section in account settings page")
    public ToolResult openBlockCompanySection() {
        try {
            accountSettingPage.navigateToBlocking();
            return new ToolResult(true, "Block Company section opened");
        } catch (Exception e) {
            return new ToolResult(
                    false,
                    "Failed to open Block Company section: " + rootCause(e)
            );
        }
    }

    @Tool(description = "Blocks a company in account settings given that Block Company section in account settings page is already opened")
    public ToolResult blockCompany(
            @ToolParam(description = "Exact company name to block")
            String companyName
    ) {
        try {
            accountSettingPage.setBlockCompany(companyName);
            return new ToolResult(
                    true,
                    "Company '" + companyName + "' blocked successfully"
            );
        } catch (Exception e) {
            return new ToolResult(
                    false,
                    "Failed to block company '" + companyName + "': " + rootCause(e)
            );
        }
    }

    @Tool(description = "Returns the list of currently blocked companies")
    public Object listBlockedCompanies() {
        try {
            return accountSettingPage.getListOfBlockCompanies();
        } catch (Exception e) {
            return new ToolResult(
                    false,
                    "Failed to retrieve blocked companies: " + rootCause(e)
            );
        }
    }

    private String rootCause(Throwable e) {
        Throwable cause = e;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause.getClass().getSimpleName();
    }
}