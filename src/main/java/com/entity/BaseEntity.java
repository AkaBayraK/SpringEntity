package com.entity;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable, Cloneable{

    private static final long serialVersionUID = 7214433176459979236L;
    
    //@JsonIgnore
    public abstract Long getId();
    //@JsonIgnore
    public abstract void setId(Long id);
    
    public BaseEntity(){
        super();
    }

}