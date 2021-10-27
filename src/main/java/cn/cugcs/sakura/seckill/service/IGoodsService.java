package cn.cugcs.sakura.seckill.service;

import cn.cugcs.sakura.seckill.entity.Goods;
import cn.cugcs.sakura.seckill.vo.GoodsVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sakura
 * @since 2021-10-25
 */
public interface IGoodsService extends IService<Goods> {
    List<GoodsVO> listSeckillGoods();

    GoodsVO getSeckillGoodsByGoodsId(Long goodsId);
}
