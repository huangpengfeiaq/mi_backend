package com.springboot.framework.controller.request;

import lombok.Data;

@Data
public class ProductCommentInsert {
    //商品编号
    private Integer productId;
    //用户编号
    private Integer userId;
    //商品评论星级数
    private Integer commentStar;
    //商品评论点赞数
    private Integer commentLike;
    //商品评论内容
    private String commentContent;
    //商品评论图片（临时）
    private String commentPicture;
    //评论留言（临时）
    private String commentReply;
}
