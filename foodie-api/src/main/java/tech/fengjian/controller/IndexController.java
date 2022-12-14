package tech.fengjian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.fengjian.enums.YesOrNo;
import tech.fengjian.pojo.Carousel;
import tech.fengjian.pojo.Category;
import tech.fengjian.pojo.vo.CategoryVO;
import tech.fengjian.pojo.vo.NewItemsVO;
import tech.fengjian.service.CarouselService;
import tech.fengjian.service.CategoryService;
import tech.fengjian.utils.JSONResult;

import java.util.List;

@Api(value = "首页", tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    @GetMapping("/carousel")
    public JSONResult carousel() {

        List<Carousel> carouselList = carouselService.queryAll(YesOrNo.YES.type);

        return JSONResult.ok(carouselList);
    }

    /**
     * 首页分类展示需求：
     * 第一次刷新主页查询大分类，渲染展示到首页
     * 如果鼠标移到大分类，则加载其子分类内容，如果已经存在子分类，则不需要加载（懒加载）
     */
    @ApiOperation(value = "获取商品分类（一级分类）", notes = "获取商品分类（一级分类）", httpMethod = "GET")
    @GetMapping("/cats")
    public JSONResult cats() {

        List<Category> result = this.categoryService.queryAllRootLevelCat();
        return JSONResult.ok(result);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public JSONResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类Id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return JSONResult.errorMsg("分类不存在");
        }
        List<CategoryVO> result = this.categoryService.getSubCatList(rootCatId);
        return JSONResult.ok(result);
    }

    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据", notes = "查询每个一级分类下的最新6条商品数据", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public JSONResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类Id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return JSONResult.errorMsg("分类不存在");
        }

        List<NewItemsVO> result = this.categoryService.getSixNewItemLazy(rootCatId);
        return JSONResult.ok(result);
    }




}
