package tech.fengjian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tech.fengjian.mapper.CategoryMapper;
import tech.fengjian.mapper.CategoryMapperCustom;
import tech.fengjian.pojo.Category;
import tech.fengjian.pojo.vo.CategoryVO;
import tech.fengjian.pojo.vo.NewItemsVO;
import tech.fengjian.service.CategoryService;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    /**
     * 查询所有一级分类
     *
     * @return List<Category>
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return categoryList;
    }

    /**
     * 根据一级分类 id 查询所有子分类
     *
     * @param rootCatId 一级分类 id
     * @return 子分类列表
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        List<CategoryVO> subCatList = categoryMapperCustom.getSubCatList(rootCatId);
        return subCatList;
    }

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     *
     * @param rootCatId 一级分类
     * @return 6条最新商品数据
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewItemsVO> getSixNewItemLazy(Integer rootCatId) {
        Map<String,Object> map = new HashMap<>();
        map.put("rootCatId",rootCatId);
        List<NewItemsVO> newItemsVOList = categoryMapperCustom.getSixNewItemLazy(map);
        return newItemsVOList;
    }
}
