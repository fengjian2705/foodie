package tech.fengjian.service;

import tech.fengjian.pojo.bo.SubmitOrderBO;
import tech.fengjian.pojo.vo.OrderVO;

public interface OrderService {

    OrderVO createOrder(SubmitOrderBO submitOrderBO);

    void closeOrder();
}
