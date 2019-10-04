package com.yyl.edu.controller.admin;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyl.common.constants.ResultCodeEnum;
import com.yyl.common.exception.GuliException;
import com.yyl.common.vo.Result;
import com.yyl.edu.entity.Teacher;
import com.yyl.edu.query.TeacherQuery;
import com.yyl.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/edu/teacher")
@CrossOrigin   //跨域
@Api(description = "讲师管理")
public class TeacherAdminController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping
    public Result list(){
        List<Teacher> list = teacherService.list(null);
        return Result.ok()
                .message("查询讲师列表成功")
                .data("items", list);
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public Result PapeQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            TeacherQuery teacherQuery
    ){

        if (page <= 0 || limit <= 0){
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }

        Page<Teacher> pageParam = new Page<>(page, limit);

        teacherService.pageQuery(pageParam, teacherQuery);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return Result.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping("{id}")
    public Result removeById(
            @ApiParam(name = "id", value = "讲师id", required = true)
            @PathVariable String id){
        teacherService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public Result save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestParam Teacher teacher
    ){
        teacherService.save(teacher);
        return Result.ok();
    }

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("{id}")
    public Result getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id
    ){

        Teacher teacher = teacherService.getById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public Result updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher
    ){
        teacher.setId(id);
        teacherService.updateById(teacher);
        return Result.ok();
    }

/*
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public Result PageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit
    ){

        Page<Teacher> pageParam = new Page<>(page, limit);

        teacherService.page(pageParam, null);
        List<Teacher> records = pageParam.getRecords();
        Long total = pageParam.getTotal();

        return Result.ok().data("total", total).data("rows", records);
    }
 */
}