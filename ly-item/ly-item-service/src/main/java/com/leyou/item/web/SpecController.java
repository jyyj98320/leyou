package com.leyou.item.web;

import com.leyou.item.dto.SpecGroupDTO;
import com.leyou.item.dto.SpecParamDTO;
import com.leyou.item.service.SpecGroupService;
import com.leyou.item.service.SpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spec")
public class SpecController {

    @Autowired
    private SpecGroupService groupService;

    @Autowired
    private SpecParamService paramService;

    /**
     * 根据分类id查询对应的规格参数组信息
     * @param cid
     * @return
     */
    @GetMapping("/groups/of/category")
    public ResponseEntity<List<SpecGroupDTO>> queryGroupByCategory(@RequestParam("id")Long cid){
        return ResponseEntity.ok(this.groupService.queryGroupByCategory(cid));
    }

    /**
     * 规格参数的条件查询
     * @param gid
     * @param cid
     * @param searching
     * @return
     */
    @GetMapping("/params")
    public ResponseEntity<List<SpecParamDTO>> queryParams(
            @RequestParam(value = "groupId",required = false)Long gid,
            @RequestParam(value = "categoryId",required = false)Long cid,
            @RequestParam(value = "searching",required = false)Boolean searching){

        return ResponseEntity.ok(this.paramService.queryParams(gid,cid,searching));
    }

}
