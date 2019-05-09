package com.huahua.spit.article.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentMongoDB implements Serializable {

    private static final long serialVersionUID = -6168066449374014907L;
    @Id
    private String _id;
    private String articleid;
    private String content;
    private String userid;
    private String parentid;
    private Date publishdate;
}
