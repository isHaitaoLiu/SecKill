package cn.cugcs.sakura.seckill.service.impl;

import cn.cugcs.sakura.seckill.entity.SeckillOrder;
import cn.cugcs.sakura.seckill.mapper.SeckillOrderMapper;
import cn.cugcs.sakura.seckill.service.ISeckillOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements ISeckillOrderService {
    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Override
    public SeckillOrder getByUserIdAndGoodsId(Long userId, Long goodsId) {
        QueryWrapper<SeckillOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("goods_id", goodsId);
        return seckillOrderMapper.selectOne(queryWrapper);
    }
}
