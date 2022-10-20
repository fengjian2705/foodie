package tech.fengjian.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.fengjian.service.OrderService;
import tech.fengjian.utils.DateUtil;

import java.util.Date;

@Component
public class OrderJob {

    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "0/3 * * * * ?")
    public void autoCloseOrder() {
        System.out.println("执行定时任务，当前时间：" +
                DateUtil.dateToString(new Date(), DateUtil.DATETIME_PATTERN));
        orderService.closeOrder();
    }
}