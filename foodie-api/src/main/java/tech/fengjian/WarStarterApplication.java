package tech.fengjian;

// 打包war [第四步] 启动

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class WarStarterApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        // 指向 Application 这个 SpringBoot 启动类
        return builder.sources(Application.class);
    }
}
