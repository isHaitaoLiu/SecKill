package cn.cugcs.sakura.seckill.mapper;

import cn.cugcs.sakura.seckill.entity.Goods;
import cn.cugcs.sakura.seckill.vo.GoodsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sakura
 * @since 2021-10-25
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    List<GoodsVO> selectAllGoodsVO();

    GoodsVO selectGoodsVOById(Long goodsId);
}
