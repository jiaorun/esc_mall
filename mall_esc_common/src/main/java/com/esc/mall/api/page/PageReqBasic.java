package com.esc.mall.api.page;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用分页请求基础类
 * @author jiaorun
 * @date 2021/08/16 15:55
 **/
@Data
public class PageReqBasic implements Serializable {

    private static final long serialVersionUID = 6193506023811147373L;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

}
