package com.leyou.item.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leyou.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("tb_brand")
@EqualsAndHashCode(callSuper = false)
public class Brand  extends BaseEntity {
    @TableId
    private Long id;
    private String name;
    private String letter;
    private String image;
}
