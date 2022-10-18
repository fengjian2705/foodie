package tech.fengjian.mapper;

import org.apache.ibatis.annotations.Param;
import tech.fengjian.pojo.vo.CategoryVO;
import tech.fengjian.pojo.vo.NewItemsVO;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom{

    /**
     * 根据一级分类 id 查询子分类
     * @param rootCatId 一级分类id
     * @return List<CategoryVO>
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    List<NewItemsVO> getSixNewItemLazy(@Param("paramMap") Map<String, Object> map);
}