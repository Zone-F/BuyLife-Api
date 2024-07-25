package com.buylife.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.buylife.entity.Items;
import com.buylife.service.ItemsService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
 * 商品表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
@RestController
@RequestMapping("/api/items")
@Tag(name = "商品表控制器")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Operation(
        summary = "分页查询",
        description = "分页查询",
        responses = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
        }
    )
    @PostMapping("/page")
    public ResponseEntity<Page<Items>> findPage(@RequestBody Items params) {
        Page<Items> result = itemsService.findPage(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 列表查询
     *
     * @param params
     * @return
     */
    @Operation(
        summary = "列表查询",
        description = "列表查询",
        responses = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
        }
    )
    @PostMapping("/list")
    public ResponseEntity<List<Items>> findList(@RequestBody Items params) {
        List<Items> result = itemsService.findList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @Operation(
        summary = "查询",
        description = "查询详情",
        responses = {
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Items.class)))
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Items> findById(@PathVariable("id") Long id) {
        Items items = itemsService.findById(id);
        return ResponseEntity.ok(items);
    }

    /**
     * 新增
     *
     * @param items
     * @return
     */
    @Operation(
        summary = "新增",
        description = "新增数据",
        responses = {
            @ApiResponse(responseCode = "200", description = "操作成功")
        }
    )
    @PostMapping
    public ResponseEntity<Boolean> insert(@Validated @RequestBody Items items) {
        boolean result = itemsService.insert(items);
        return ResponseEntity.ok(result);
    }

    /**
     * 修改
     *
     * @param items
     * @return
     */
    @Operation(
        summary = "修改",
        description = "修改数据",
        responses = {
            @ApiResponse(responseCode = "200", description = "操作成功")
        }
    )
    @PutMapping
    public ResponseEntity<Boolean> update(@Validated @RequestBody Items items) {
        boolean result = itemsService.update(items);
        return ResponseEntity.ok(result);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Operation(
        summary = "删除",
        description = "删除数据",
        responses = {
            @ApiResponse(responseCode = "200", description = "操作成功")
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") Long id) {
        int result = itemsService.delete(id);
        return ResponseEntity.ok(result);
    }

}
