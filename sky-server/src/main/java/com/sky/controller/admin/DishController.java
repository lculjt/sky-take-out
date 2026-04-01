package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {
    @Autowired
    DishService dishService;

    @PostMapping
    public Result save( @RequestBody DishDTO dishDTO) {
        log.info("保存菜品信息：{}", dishDTO);
        dishService.save(dishDTO);
        // 实现保存菜品的功能
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询：{}", dishPageQueryDTO);
        PageResult page = dishService.page(dishPageQueryDTO);
        // 实现菜品分页查询的功能
        return Result.success(page);
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids) {
        log.info("删除菜品信息：{}", ids);
        dishService.delete(ids);
        // 实现删除菜品的功能
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<DishVO> get(@PathVariable Long id) {
        log.info("查询菜品信息：{}", id);
        DishVO dishVO = dishService.getDishVoById(id);
        // 实现根据ID查询菜品的功能
        return Result.success(dishVO);
    }

    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("更新菜品信息：{}", dishDTO);
        dishService.update(dishDTO);
        // 实现更新菜品的功能
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status, @RequestParam Long id) {
        log.info("更新菜品状态：{}", status);
        dishService.updateStatus(id, status);
        // 实现更新菜品状态的功能
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Dish>> list(Long categoryId) {
        log.info("查询菜品列表");
        List<Dish> dishVOList = dishService.list(categoryId);
        // 实现查询菜品列表的功能
        return Result.success(dishVOList);
    }
}
