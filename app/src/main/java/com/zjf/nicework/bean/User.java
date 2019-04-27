package com.zjf.nicework.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author jingfeng
 * @date 2019-04-27 13:48
 * @email 15919820315@163.com
 */
@Entity(
        nameInDb = "USERS",
        indexes = {
                @Index(value = "name DESC")
        }
)
public class User {
    @Id(autoincrement = true)
    private Long id;

    @Index(name = "usercode_index", unique = true)
    @NotNull
    private String usercode;

    @Property(nameInDb = "userName")
    @NotNull
    private String name;

    private String userAddress;

    @Transient
    private int tempUserSign;

    @Generated(hash = 1791218623)
    public User(Long id, @NotNull String usercode, @NotNull String name,
                String userAddress) {
        this.id = id;
        this.usercode = usercode;
        this.name = name;
        this.userAddress = userAddress;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsercode() {
        return this.usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

}
