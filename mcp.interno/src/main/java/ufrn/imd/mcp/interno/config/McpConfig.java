package ufrn.imd.mcp.interno.config;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ufrn.imd.mcp.interno.tool.CalendarTools;

@Configuration
@RefreshScope
public class McpConfig {

    @Bean
    public ToolCallbackProvider callbackCalendarTools(CalendarTools calendarTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(calendarTools)
                .build();
    }
}
