package tech.fengjian.service;

import tech.fengjian.pojo.Items;
import tech.fengjian.pojo.ItemsImg;
import tech.fengjian.pojo.ItemsParam;
import tech.fengjian.pojo.ItemsSpec;
import tech.fengjian.pojo.vo.CommentLevelCountsVO;
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


    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    PagedGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize);
}
