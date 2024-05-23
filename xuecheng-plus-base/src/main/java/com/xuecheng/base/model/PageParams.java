package com.xuecheng.base.model;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PageParams {

    private Long pageNum = 1L;

    private Long pageSize = 10L;

    public PageParams() {
    }

    public PageParams(Long pageNum, Long pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
