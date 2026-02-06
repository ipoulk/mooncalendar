package com.ifigeneia.mooncalendar.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="translation")
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable=false)
    private String en;
    private String de;
    private String description;
    private String comment;

    @Column(name="created_at",nullable=false)
    private LocalDateTime createdAt;

    @Column(name="updated_at",nullable=false)
    private LocalDateTime updatedAt;

    protected Translation(){}

    public Translation(
                       String en,
                       String de,
                       String description,
                       String comment
                      ){
        this.en = en;
        this.de = de;
        this.description = description;
        this.comment = comment;


    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getEn(){
        return en;
    }

    public void setEn(String en){
        this.en = en;
    }

    public String getDe(){
        return de;
    }

    public void setDe(String de){
        this.de = de;
    }

    public String getDescription(){ return description;}

    public void setDescription(String description){this.description=description;}

    public String getComment(){ return comment;}

    public void setComment(String comment){this.comment=comment;}

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }
}


