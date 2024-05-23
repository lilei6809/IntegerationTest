package com.xuecheng.content.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
public class CourseBaseInfoController {



    @PostMapping("/course/list")
    public PageResult<CourseBase> courseList(PageParams pageParams,
                                             @RequestBody QueryCourseParamsDto queryCourseParamsDto){
        PageResult<CourseBase> pageResult = new PageResult<>(new LinkedList<>(), 2, 2, 10);
        pageResult.getItems().add(new CourseBase());
        return pageResult;
    }
}
