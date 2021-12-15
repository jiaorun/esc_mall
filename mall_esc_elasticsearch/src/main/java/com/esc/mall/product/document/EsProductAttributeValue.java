package com.esc.mall.product.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 搜索系统 商品属性信息
 *
 * @author jiaorun
 * @date 2021/11/9 11:29
 **/
@Data
@Component
public class EsProductAttributeValue implements Serializable {

    private static final long serialVersionUID = 2340751892714311993L;

    private Long id;

    private Long productAttributeId;

    @Field(type = FieldType.Keyword)
    private String value;

    //属性参数 0->规格 1->参数
    private Integer type;

    @Field(type = FieldType.Keyword)
    private String name;
}
