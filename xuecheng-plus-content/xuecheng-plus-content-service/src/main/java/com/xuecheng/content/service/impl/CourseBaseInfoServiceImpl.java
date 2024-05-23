package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto dto) {
        LambdaQueryWrapper<CourseBase> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.isNotEmpty(dto.getCourseName()), CourseBase::getName, dto.getCourseName());
        wrapper.eq(StringUtils.isNotEmpty(dto.getAuditStatus()), CourseBase::getAuditStatus, dto.getAuditStatus());

        Page<CourseBase> page = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());

        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, wrapper);

        List<CourseBase> records = pageResult.getRecords();

        long total = pageResult.getTotal();

        PageResult<CourseBase> courseBasePageResult = new PageResult<>(records, total, pageParams.getPageNum(), pageParams.getPageSize());
        return courseBasePageResult;
    }

    @Override
    public CourseBase queryCourseById(Long id) {
        LambdaQueryWrapper<CourseBase> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseBase::getId, id);

        CourseBase courseBase = courseBaseMapper.selectById(id);
        return courseBase;
    }
}
