package tech.fengjian.service;

import tech.fengjian.pojo.Carousel;

import java.util.List;

public interface CarouselService {

    /**
     * 查询所有轮播图
     *
     * @param isShow 是否显示
     * @return 轮播图列表
     */
    List<Carousel> queryAll(Integer isShow);
}
