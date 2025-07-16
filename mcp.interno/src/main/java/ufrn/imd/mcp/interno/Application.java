package ufrn.imd.mcp.interno;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ufrn.imd.mcp.interno.tool.CalendarTools;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ToolCallbackProvider callbackCalendarTools(CalendarTools calendarTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(calendarTools)
                .build();
    }
}
