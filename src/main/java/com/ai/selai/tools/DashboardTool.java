package com.ai.selai.tools;

import com.ai.selai.pages.DashBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DashboardTool {

    private final DashBoard dashBoard;

    @Tool(description = "Open Account Settings from dashboard")
    public String openAccountSettings() {
        try {
            dashBoard.navigateToAccountSetting();
            return "Navigated to Account Settings";
        } catch (Exception e) {
            return "Failed to navigate to Account Settings: " + e.getMessage();
        }
    }

    @Tool(description = "Open Profile Settings from dashboard")
    public String openProfileSettings() {
        try {
            dashBoard.navigateToSetting();
            return "Navigated to Profile Settings";
        } catch (Exception e) {
            return "Failed to navigate to Profile Settings: " + e.getMessage();
        }
    }

    @Tool(description = "Open FAQ page from dashboard")
    public String openFaq() {
        try {
            dashBoard.navigateToFaq();
            return "Navigated to FAQ page";
        } catch (Exception e) {
            return "Failed to navigate to FAQ page: " + e.getMessage();
        }
    }

    @Tool(description = "Logout from the application")
    public String logout() {
        try {
            dashBoard.logout();
            return "Logged out successfully";
        } catch (Exception e) {
            return "Logout failed: " + e.getMessage();
        }
    }
}