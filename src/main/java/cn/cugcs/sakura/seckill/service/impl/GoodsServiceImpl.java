package cn.cugcs.sakura.seckill.service.impl;

import cn.cugcs.sakura.seckill.entity.Goods;
import cn.cugcs.sakura.seckill.mapper.GoodsMapper;
import cn.cugcs.sakura.seckill.service.IGoodsService;
import cn.cugcs.sakura.seckill.vo.GoodsVO;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sakura
 * @since 2021-10-25
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * @Author sakura
     * @Description 获取秒杀商品列表
     * @Date 2021/10/26
     * @Param []
     * @return java.util.List<cn.cugcs.sakura.seckill.vo.GoodsVO>
     **/
    @Override
    public List<GoodsVO> listSeckillGoods() {
        return goodsMapper.selectAllGoodsVO();
    }

    @Override
    public GoodsVO getSeckillGoodsByGoodsId(Long goodsId) {
        return goodsMapper.selectGoodsVOById(goodsId);
    }

    @Override
    public Integer updateStockCountByGoodsId(Goods goods, Long id) {
        UpdateWrapper<Goods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("goods_stock = goods_stock - 1");
        updateWrapper.gt("goods_stock", 0);
        updateWrapper.eq("id", id);
        return goodsMapper.update(goods, updateWrapper);
    }
}
