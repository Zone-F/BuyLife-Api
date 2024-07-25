package com.buylife.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.buylife.entity.CheckIns;
import com.buylife.service.CheckInsService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
 * 打卡记录表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
@RestController
@RequestMapping("/api/checkIns")
@Tag(name = "打卡记录表控制器")
public class CheckInsController {

    @Autowired
    private CheckInsService checkInsService;

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
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json"))
        }
    )
    @PostMapping("/page")
    public ResponseEntity<Page<CheckIns>> findPage(@RequestBody CheckIns params) {
        Page<CheckIns> result = checkInsService.findPage(params);
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
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json"))
        }
    )
    @PostMapping("/list")
    public ResponseEntity<List<CheckIns>> findList(@RequestBody CheckIns params) {
        List<CheckIns> result = checkInsService.findList(params);
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
            @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(mediaType = "application/json"))
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CheckIns> findById(@PathVariable("id") Long id) {
        CheckIns checkIns = checkInsService.findById(id);
        return ResponseEntity.ok(checkIns);
    }

    /**
     * 新增
     *
     * @param checkIns
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
    public ResponseEntity<Boolean> insert(@Validated @RequestBody CheckIns checkIns) {
        boolean result = checkInsService.insert(checkIns);
        return ResponseEntity.ok(result);
    }

    /**
     * 修改
     *
     * @param checkIns
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
    public ResponseEntity<Boolean> update(@Validated @RequestBody CheckIns checkIns) {
        boolean result = checkInsService.update(checkIns);
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
        int result = checkInsService.delete(id);
        return ResponseEntity.ok(result);
    }

}
