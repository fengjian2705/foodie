package tech.fengjian.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tech.fengjian.pojo.Items;
import tech.fengjian.pojo.ItemsImg;
import tech.fengjian.pojo.ItemsParam;
import tech.fengjian.pojo.ItemsSpec;
import tech.fengjian.pojo.vo.CommentLevelCountsVO;
import tech.fengjian.pojo.vo.ShopCartVO;
import tech.fengjian.utils.PagedGridResult;

import java.util.List;

public interface ItemService {

    /**
     * 根据商品 id 查询详情
     *
     * @param itemId 商品 id
     * @return 商品信息
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品 id 查询商品图片列表
     *
     * @param itemId 商品 id
     * @return 商品图片列表
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品 id 查询商品规格列表
     *
     * @param itemId 商品 id
     * @return 商品规格列表
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品 id 查询商品参数
     *
     * @param itemId 商品 id
     * @return 商品参数
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * 根据商品 id 查询商品评价等级数量
     *
     * @param itemId 商品 id
     * @return 商品规格列表
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品 id 查询商品评价（分页）
     *
     * @param itemId 商品 id
     * @param level  评价级别
     * @return 商品评价
     */
    PagedGridResult queryPagedComments(String itemId, Integer level,
                                       Integer page, Integer pageSize);

    /**
     * 根据关键字查询商品评价（分页）
     *
     * @param keywords 关键字
     * @param sort     排序
     * @return 商品评价
     */
    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * 根据分类 id 查询商品评价（分页）
     *
     * @param catId 分类 id
     * @param sort  排序
     * @return 商品评价
     */
    PagedGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize);

    /**
     * 根据规格ids查询购物车中最新商品数据
     *
     * @param specIds 规格id
     * @return 商品信息
     */
    List<ShopCartVO> queryItemsBySpecIds(String specIds);

    ItemsSpec queryItemsSpecById(String itemSpecId);

    @Transactional(propagation = Propagation.SUPPORTS)
    String queryItemMainImgById(String itemId);

    @Transactional(propagation = Propagation.REQUIRED)
    void decreaseItemSpecStock(String specId, int buyCounts);
}
