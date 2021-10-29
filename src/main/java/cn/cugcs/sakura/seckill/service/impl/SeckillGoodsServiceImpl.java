package cn.cugcs.sakura.seckill.service.impl;

import cn.cugcs.sakura.seckill.entity.SeckillGoods;
import cn.cugcs.sakura.seckill.mapper.SeckillGoodsMapper;
import cn.cugcs.sakura.seckill.service.ISeckillGoodsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sakura
 * @since 2021-10-25
 */
@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements ISeckillGoodsService {
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Override
    public SeckillGoods getByGoodsId(Long goodsId) {
        QueryWrapper<SeckillGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", goodsId);
        return seckillGoodsMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer updateStockCountByGoodsId(SeckillGoods seckillGoods, Long id) {
        UpdateWrapper<SeckillGoods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("stock_count = stock_count - 1");
        updateWrapper.eq("goods_id", id);
        updateWrapper.gt("stock_count", 0);
        return seckillGoodsMapper.update(seckillGoods, updateWrapper);
    }
}
