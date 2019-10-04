package com.yyl.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyl.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyl.edu.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author yyl
 * @since 2019-09-16
 */
public interface TeacherService extends IService<Teacher> {

    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);
}
