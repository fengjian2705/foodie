package tech.fengjian;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
// 扫描 mybatis 通用 mapper 所在包
@MapperScan(basePackages = "tech.fengjian.mapper")
// 扫描所有包及相关组件包
@ComponentScan(basePackages = {"tech.fengjian", "org.n3r.idworker"})
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
