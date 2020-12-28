package com.mmc.mbdao.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user", indexes = {
//        @Index(name = "username", columnList = "user_name")
})
public class UserEntity extends BaseEntity implements Serializable {

    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
