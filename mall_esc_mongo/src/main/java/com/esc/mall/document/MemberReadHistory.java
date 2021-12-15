package com.esc.mall.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户浏览商品历史记录
 *
 * @author jiaorun
 * @date 2021/12/14 12:20
 **/
@Document(collection = "member_read_history")   //标识映射到MongoDB文档上的领域对象
@Data
public class MemberReadHistory implements Serializable {

    private static final long serialVersionUID = -6498208061336476372L;

    @Id //标识某个域为ID域
    private String id;

    @Indexed    //标识某个字段为MongoDB的索引字段 
    private Long memberId;

    private String memberNickname;

    private String memberIcon;

    @Indexed
    private Long productId;

    private String productName;

    private String productPic;

    private String productSubTitle;

    private String productPrice;

    private Date createTime;
}
