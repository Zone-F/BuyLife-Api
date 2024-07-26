package com.buylife.controller;

import com.buylife.common.ResponseResult;
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

import com.buylife.entity.Users;
import com.buylife.service.UsersService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
 * 用户信息表
 *
 * @author zone98f
 * @since 2024-07-23 17:21:18
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "用户信息表控制器")
public class UsersController {

    @Autowired
    private UsersService usersService;

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
    public ResponseEntity<Page<Users>> findPage(@RequestBody Users params) {
        Page<Users> result = usersService.findPage(params);
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
    public ResponseEntity<List<Users>> findList(@RequestBody Users params) {
        List<Users> result = usersService.findList(params);
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
                    schema = @Schema(implementation = Users.class)
                )
            )
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@Parameter(description = "用户ID") @PathVariable("id") Long id) {
        Users users = usersService.findById(id);
        return ResponseEntity.ok(users);
    }

    /**
     * 新增
     *
     * @param users
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
//    @PostMapping
//    public ResponseEntity<Boolean> insert(@Validated @RequestBody Users users) {
//        boolean result = usersService.insert(users);
//        return ResponseEntity.ok(result);
//    }
    @PostMapping
    public ResponseResult<Boolean> insert(@Validated @RequestBody Users users) {
        boolean result = usersService.insert(users);
        return ResponseResult.success();
    }
    /**
     * 修改
     *
     * @param users
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
    public ResponseEntity<Boolean> update(@Validated @RequestBody Users users) {
        boolean result = usersService.update(users);
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
    public ResponseEntity<Integer> delete(@Parameter(description = "用户ID") @PathVariable("id") Long id) {
        int result = usersService.delete(id);
        return ResponseEntity.ok(result);
    }

}
