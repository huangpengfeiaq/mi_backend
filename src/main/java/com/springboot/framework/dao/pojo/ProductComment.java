package com.springboot.framework.dao.pojo;

import com.springboot.framework.dto.ProductCommentDTO;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class ProductComment {
    //商品评论编号
    @Id
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

    public String getCommentReply() {
        return commentReply;
    }

    public void setCommentReply(String commentReply) {
        this.commentReply = commentReply == null ? null : commentReply.trim();
    }

    public ProductComment() {
    }

    public ProductComment(ProductCommentDTO productCategoryDTO) {
        this.commentId = productCategoryDTO.getCommentId();
        this.productId = productCategoryDTO.getProductId();
        this.userId = productCategoryDTO.getUserId();
        this.commentStar = productCategoryDTO.getCommentStar();
        this.commentLike = productCategoryDTO.getCommentLike();
        this.commentContent = productCategoryDTO.getCommentContent();
        this.commentPicture = productCategoryDTO.getCommentPicture();
        this.createBy = productCategoryDTO.getCreateBy();
        this.createDate = productCategoryDTO.getCreateDate();
        this.updateBy = productCategoryDTO.getUpdateBy();
        this.updateDate = productCategoryDTO.getUpdateDate();
        this.status = productCategoryDTO.getStatus();
        this.commentReply = productCategoryDTO.getCommentReply();
    }
}