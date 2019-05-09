package com.huahua.friend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_nofriend")
@Data
@IdClass(NOFriend.class)
public class NOFriend implements Serializable {
    private static final long serialVersionUID = -5236820982533216953L;

    @Id
    private String userid;
    @Id
    private String friendid;
}
