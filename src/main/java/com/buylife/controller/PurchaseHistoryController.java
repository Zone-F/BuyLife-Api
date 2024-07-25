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

import com.buylife.entity.PurchaseHistory;
import com.buylife.service.PurchaseHistoryService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
 * 用户购买历史表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
@RestController
@RequestMapping("/api/purchaseHistory")
@Tag(name = "用户购买历史表控制器")
public class PurchaseHistoryController {

    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

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
            @ApiResponse(
                responseCode = "200",
                description = "查询成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Page.class)
                )
            )
        }
    )
    @PostMapping("/page")
    public ResponseEntity<Page<PurchaseHistory>> findPage(@RequestBody PurchaseHistory params) {
        Page<PurchaseHistory> result = purchaseHistoryService.findPage(params);
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
            @ApiResponse(
                responseCode = "200",
                description = "查询成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = List.class)
                )
            )
        }
    )
    @PostMapping("/list")
    public ResponseEntity<List<PurchaseHistory>> findList(@RequestBody PurchaseHistory params) {
        List<PurchaseHistory> result = purchaseHistoryService.findList(params);
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
            @ApiResponse(
                responseCode = "200",
                description = "查询成功",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PurchaseHistory.class)
                )
            )
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseHistory> findById(@PathVariable("id") Long id) {
        PurchaseHistory purchaseHistory = purchaseHistoryService.findById(id);
        return ResponseEntity.ok(purchaseHistory);
    }

    /**
     * 新增
     *
     * @param purchaseHistory
     * @return
     */
    @Operation(
        summary = "新增",
        description = "新增数据",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "操作成功"
            )
        }
    )
    @PostMapping
    public ResponseEntity<Boolean> insert(@Validated @RequestBody PurchaseHistory purchaseHistory) {
        boolean result = purchaseHistoryService.insert(purchaseHistory);
        return ResponseEntity.ok(result);
    }

    /**
     * 修改
     *
     * @param purchaseHistory
     * @return
     */
    @Operation(
        summary = "修改",
        description = "修改数据",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "操作成功"
            )
        }
    )
    @PutMapping
    public ResponseEntity<Boolean> update(@Validated @RequestBody PurchaseHistory purchaseHistory) {
        boolean result = purchaseHistoryService.update(purchaseHistory);
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
            @ApiResponse(
                responseCode = "200",
                description = "操作成功"
            )
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") Long id) {
        int result = purchaseHistoryService.delete(id);
        return ResponseEntity.ok(result);
    }

}
