package tech.fengjian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tech.fengjian.mapper.CarouselMapper;
import tech.fengjian.pojo.Carousel;
import tech.fengjian.service.CarouselService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * 查询所有轮播图
     *
     * @param isShow 是否显示 {@link tech.fengjian.enums.YesOrNo}
     * @return 轮播图列表
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example example = new Example(Carousel.class);
        example.orderBy("sort").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow", isShow);
        List<Carousel> carouselList = carouselMapper.selectByExample(example);
        return carouselList;
    }
}
