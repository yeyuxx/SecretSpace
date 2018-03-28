package head.secretspace.entity;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "SECRETSPACE".
 */
@Entity
public class Secretspace {

    @Id
    private Long id;
    private String name;
    private String address;
    private Integer age;
    private String time;

    @Generated
    public Secretspace() {
    }

    public Secretspace(Long id) {
        this.id = id;
    }

    @Generated
    public Secretspace(Long id, String name, String address, Integer age, String time) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}