package com.leyou.item.web;

import com.leyou.item.dto.CategoryDTO;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 根据分类id，查询商品分类
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> queryCategoryById(@PathVariable("id")Long id){
        return ResponseEntity.ok(new CategoryDTO(this.categoryService.getById(id)));
    }


    /**
     * 根据分类的id的集合查询对应的分类的集合
     * @param ids
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<CategoryDTO>> queryCategoryByIds(
            @RequestParam("ids")List<Long> ids){

        return ResponseEntity.ok(this.categoryService.queryCategoryByIds(ids));
    }

    /**
     * 根据分类的父id查询对应的所有的子
     * @param pid
     * @return
     */
    @GetMapping("/of/parent")
    public ResponseEntity<List<CategoryDTO>> queryCategoryByPid(@RequestParam("pid")Long pid){
        return ResponseEntity.ok(this.categoryService.queryCategoryByPid(pid));
    }

    /**
     * 根据品牌id查询对应的分类id集合
     * @param bid
     * @return
     */
    @GetMapping("/of/brand")
    public ResponseEntity<List<CategoryDTO>> queryCategoryByBrand(@RequestParam(value = "id")Long bid){

        return ResponseEntity.ok(this.categoryService.queryCategoryByBrand(bid));
    }



}
