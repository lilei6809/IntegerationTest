package com.xuecheng.content.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CourseBaseInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void shouldReturnDefaultPage() throws Exception{
        // 创建请求参数
        PageParams pageParams = new PageParams();

        QueryCourseParamsDto dto = new QueryCourseParamsDto();

        // 请求参数序列化为 Json
        String pageParamsJson = objectMapper.writeValueAsString(pageParams);
        String dtoJson = objectMapper.writeValueAsString(dto);

        // 执行 POST 请求并验证响应
        mockMvc.perform(post("/course/list")
                        .param("pageParams", pageParamsJson)
                        .content(dtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.items[0]").exists())
                .andExpect(jsonPath("$.counts").value(2))
                .andExpect(jsonPath("$.page").value(2))
                .andExpect(jsonPath("$.pageSize").value(10))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();



    }
}
