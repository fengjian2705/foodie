package tech.fengjian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.fengjian.pojo.bo.SubmitOrderBO;
import tech.fengjian.pojo.vo.OrderVO;
import tech.fengjian.service.OrderService;
import tech.fengjian.utils.CookieUtils;
import tech.fengjian.utils.JSONResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "订单相关", tags = {"订单相关api接口"})
@RestController
@RequestMapping("/orders")
public class OrdersController extends BaseController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public JSONResult create(@RequestBody SubmitOrderBO submitOrderBO,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        System.out.println(submitOrderBO.toString());

        // 1. 创建订单
        OrderVO orderVO =  orderService.createOrder(submitOrderBO);
        String orderId = orderVO.getOrderId();
        // 2. 创建订单后，移除购物车中已结算（已提交）的商品
        CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "",true);

        // 3. 向支付中心发送当前订单，用于保存支付中心的订单数据

        return JSONResult.ok(orderId);
    }


}