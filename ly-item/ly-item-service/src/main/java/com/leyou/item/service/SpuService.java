package com.leyou.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leyou.item.entity.Spu;

import java.util.List;

public interface SpuService extends IService<Spu> {
    List<SpuService> pageQuery();

}

