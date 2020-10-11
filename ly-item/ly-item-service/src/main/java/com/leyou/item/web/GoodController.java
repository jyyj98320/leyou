package com.leyou.item.web;

import com.leyou.common.dto.PageDTO;
import com.leyou.item.dto.SkuDTO;
import com.leyou.item.dto.SpecParamDTO;
import com.leyou.item.dto.SpuDTO;
import com.leyou.item.dto.SpuDetailDTO;
import com.leyou.item.service.SkuService;
import com.leyou.item.service.SpuDetailService;
import com.leyou.item.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodController {

    @Autowired
    private SkuService skuService;

    @Autowired
    private SpuService spuService;

    @Autowired
    private SpuDetailService detailService;

    @GetMapping("/spu/page")
    public ResponseEntity<PageDTO<SpuDTO>> pageQuery(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "brandId", required = false) Long bid,
            @RequestParam(value = "categoryId", required = false) Long cid,
            @RequestParam(value = "saleable", required = false) Boolean saleable) {

        return ResponseEntity.ok(this.spuService.pageQuery(page, rows, id, bid, cid, saleable));
    }


    /**
     * 上下架状态修改
     *
     * @param spuId
     * @param saleable
     * @return
     */
    @PutMapping("/saleable")
    public ResponseEntity<Void> modifySaleable(
            @RequestParam("id") Long spuId,
            @RequestParam("saleable") Boolean saleable) {

        this.spuService.modifySaleable(spuId, saleable);

        return ResponseEntity.ok().build();
    }


    /**
     * 商品新增
     *
     * @param spuDTO
     * @return
     */
    @PostMapping("/spu")
    public ResponseEntity<Void> saveGoods(
            @RequestBody SpuDTO spuDTO) {
        this.spuService.saveGoods(spuDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据spuId查询spu即spuDetail和sku
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<SpuDTO> querySpuById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.spuService.querySpuById(id));
    }

    /**
     * 商品修改
     *
     * @param spuDTO
     * @return
     */
    @PutMapping("/spu")
    public ResponseEntity<Void> updateGoods(
            @RequestBody SpuDTO spuDTO) {
        this.spuService.updateGoods(spuDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * 根据sku的id集合查询对应的sku集合
     *
     * @param ids
     * @return
     */
    @GetMapping("/sku/list")
    public ResponseEntity<List<SkuDTO>> querySkuByIds(@RequestParam("ids") List<Long> ids) {

        return ResponseEntity.ok(this.skuService.querySkuByIds(ids));
    }

    /**
     * 根据spuId查询对应的sku的集合
     *
     * @param spuId
     * @return
     */
    @GetMapping("/sku/of/spu")
    public ResponseEntity<List<SkuDTO>> querySkuBySpuId(@RequestParam("id") Long spuId) {

        return ResponseEntity.ok(this.skuService.querySkuBySpuId(spuId));
    }


    /**
     * 根据spuId查询对应的额spuDetail
     *
     * @param spuId
     * @return
     */
    @GetMapping("/spu/detail")
    public ResponseEntity<SpuDetailDTO> querySpuDetailById(@RequestParam("id") Long spuId) {

        return ResponseEntity.ok(new SpuDetailDTO(this.detailService.getById(spuId)));
    }

    /**
     * 根据id查询spu的基本信息
     * @param id
     * @return
     */
    @GetMapping("/spu/{id}")
    public ResponseEntity<SpuDTO> querySpu(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new SpuDTO(this.spuService.getById(id)));
    }


    /**
     * 根据spuId以及是否可搜索查询规格参数集合信息
     * @param id
     * @param searching
     * @return
     */
    @GetMapping("/spec/value")
    public ResponseEntity<List<SpecParamDTO>> queryParams(
            @RequestParam("id") Long id,
            @RequestParam(value = "searching",required = false) Boolean searching){

        return ResponseEntity.ok(this.spuService.queryParams(id,searching));
    }

}
