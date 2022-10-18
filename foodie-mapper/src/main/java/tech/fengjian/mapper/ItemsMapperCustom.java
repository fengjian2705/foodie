package tech.fengjian.mapper;

import org.apache.ibatis.annotations.Param;
import tech.fengjian.pojo.vo.ItemCommentVO;
import tech.fengjian.pojo.vo.SearchItemsVO;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {

    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);

    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);

    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap")Map<String,Object> map);
}