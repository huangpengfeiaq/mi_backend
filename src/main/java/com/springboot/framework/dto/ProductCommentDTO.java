package com.springboot.framework.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductCommentDTO {
    //商品评论编号
    private Integer commentId;
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
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;
    //评论留言（临时）
    private String commentReply;

    public ProductCommentDTO() {
    }

    //新增
    public ProductCommentDTO(Integer productId, Integer userId, Integer commentStar, Integer commentLike, String commentContent, String commentPicture, String commentReply, String createBy) {
        this.productId = productId;
        this.userId = userId;
        this.commentStar = commentStar;
        this.commentLike = commentLike;
        this.commentContent = commentContent;
        this.commentPicture = commentPicture;
        this.createBy = createBy;
        this.commentReply = commentReply;
    }

    //更新
    public ProductCommentDTO(Integer commentId, Integer productId, Integer userId, Integer commentStar, Integer commentLike, String commentContent, String commentPicture, String commentReply, String updateBy) {
        this.commentId = commentId;
        this.productId = productId;
        this.userId = userId;
        this.commentStar = commentStar;
        this.commentLike = commentLike;
        this.commentContent = commentContent;
        this.commentPicture = commentPicture;
        this.updateBy = updateBy;
        this.commentReply = commentReply;
    }
}
