package tech.fengjian.service;

import tech.fengjian.pojo.Category;
import tech.fengjian.pojo.vo.CategoryVO;
import tech.fengjian.pojo.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {

    /**
     * 查询所有一级分类
     *
     * @return List<Category>
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类 id 查询所有子分类
     *
     * @param rootCatId 一级分类 id
     * @return 子分类列表
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     *
     * @param rootCatId 一级分类
     * @return 6条最新商品数据
     */
    List<NewItemsVO> getSixNewItemLazy(Integer rootCatId);
}
