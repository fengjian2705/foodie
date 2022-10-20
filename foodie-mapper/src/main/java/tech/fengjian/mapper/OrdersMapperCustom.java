package tech.fengjian.mapper;

import org.apache.ibatis.annotations.Param;
import tech.fengjian.pojo.OrderStatus;
import tech.fengjian.pojo.vo.MyOrdersVO;

import java.util.List;
import java.util.Map;

public interface OrdersMapperCustom {

    List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String, Object> map);

    int getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);

    List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String, Object> map);

}
