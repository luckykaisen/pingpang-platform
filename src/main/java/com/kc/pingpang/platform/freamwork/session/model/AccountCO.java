package com.kc.pingpang.platform.freamwork.session.model;

import java.io.Serializable;

public class AccountCO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String type;
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
