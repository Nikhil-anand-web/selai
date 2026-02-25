package com.ai.selai.components;

import com.ai.selai.pages.*;
import com.ai.selai.pages.DashBoard;

import com.ai.selai.tools.AccountSettingTools;
import com.ai.selai.tools.DashboardTool;
import com.ai.selai.tools.HomePageTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Ai1 {

    // These are automatically injected because of @RequiredArgsConstructor and @Component on the Page classes
    public final AccountSettingTools asp;
    public final HomePageTools hpt;
    public final DashboardTool dbt;
   private final MessageWindowChatMemory memory;

    @Bean
    public ChatClient seleniumChatClient(ChatClient.Builder builder) {
        return builder
                .defaultTools(hpt,asp,dbt)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .defaultSystem("""
                        You are a specialized Self-Healing QA Automation Engineer.\s
                                                                                                                 Your goal is to complete the user's request by interacting with a live browser.
                        
                                                                                                                 STRATEGY HIERARCHY:
                                                                                                                 1. PRIMARY (POM Mode): Always try to use the specific methods in HomePage, DashBoard, or SettingPage first. These are optimized for the current application.
                                                                                                                 2. DIAGNOSTIC: If a POM tool returns an error (e.g., Timeout, NoSuchElement, or Intercepted), do not report failure to the user immediately. Instead, use 'getCurrentPageContext' to verify your location.
                                                                                                                 3. AUTONOMOUS (Healing Mode): If the POM locators have failed, switch to full autonomous mode:
                                                                                                                    - Use 'discoverPageElements' or 'findElementByText' to identify new or changed locators.
                                                                                                                    - Use 'healClick' or 'forceClick' to bypass UI obstructions or ID changes.
                                                                                                                    - Once you find a working path, use 'reportHealing' to document the fix.
                        
                                                                                                                 REASONING RULE:
                                                                                                                 If you encounter a popup or a redirect you didn't expect, use your diagnostic tools to identify the obstacle and clear it autonomously before resuming the main task.
                                                                                                                 Always report the final status of the browser (URL/Title) to the user.
                    """)
                .build();
    }
}