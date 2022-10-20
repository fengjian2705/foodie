package tech.fengjian.mapper;

import org.apache.ibatis.annotations.Param;
import tech.fengjian.my.mapper.MyMapper;
import tech.fengjian.pojo.ItemsComments;
import tech.fengjian.pojo.vo.MyCommentVO;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom extends MyMapper<ItemsComments> {

    void saveComments(Map<String, Object> map);

    List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}