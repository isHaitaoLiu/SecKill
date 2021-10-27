package cn.cugcs.sakura.seckill.vo;

import cn.cugcs.sakura.seckill.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: seckill
 * @description: 商品展示对象
 * @author: Sakura
 * @create: 2021-10-26 17:00
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVO extends Goods {
    private BigDecimal seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
