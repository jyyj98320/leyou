package com.leyou.item.web;

import com.leyou.common.dto.PageDTO;
import com.leyou.item.dto.BrandDTO;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    /**
     * 根据品牌的id集合查询对应得品牌集合
     * @param ids
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<BrandDTO>> queryBrandByIds(@RequestParam("ids")List<Long> ids){

        return ResponseEntity.ok(this.brandService.queryBrandByIds(ids));
    }


    /**
     * 品牌分页查询
     * @param page
     * @param rows
     * @param key
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<PageDTO<BrandDTO>> pageQuery(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "key",required = false)String key){

        return ResponseEntity.ok(this.brandService.pageQuery(page,rows,key));
    }

    /**
     * 根据id查询对应的品牌信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> queryBrandById(@PathVariable("id")Long id){
        return ResponseEntity.ok(new BrandDTO(this.brandService.getById(id)));
    }

    /**
     * 根据分类查询对应的品牌信息
     * @param cid
     * @return
     */
    @GetMapping("/of/category")
    public ResponseEntity<List<BrandDTO>> queryBrandByCategory(@RequestParam("id")Long cid){
        return ResponseEntity.ok(this.brandService.queryBrandByCategory(cid));
    }

    /**
     * 品牌新增
     * @param brandDTO
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addBrand(
          BrandDTO brandDTO,
            @RequestParam("categoryIds")List<Long> cids){

        this.brandService.addBrand(brandDTO,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 品牌修改
     * @param brandDTO
     * @param cids
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateBrand(
            BrandDTO brandDTO,
            @RequestParam("categoryIds")List<Long> cids){

        this.brandService.updateBrand(brandDTO,cids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 品牌删除
     * @param bid
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable("id")Long bid){

        this.brandService.deleteBrand(bid);
        return ResponseEntity.ok().build();
    }
}
