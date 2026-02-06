package com.ifigeneia.mooncalendar.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="fasting_type")
public class FastingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;

    @Column(unique = true)
    private String code;

    @Column(nullable=false,unique = true)
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="name_translation_id",nullable=false)
    private Translation nameTranslation;

    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="description_translation_id",nullable=false)
    private Translation descriptionTranslation;

    private String comment;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="comment_translation_id",nullable=false)
    private Translation commentTranslation;

    @Column(nullable=false)
    private Boolean meat;

    @Column(nullable=false)
    private Boolean dairy;

    @Column(nullable=false)
    private Boolean fish;

    @Column(nullable=false)
    private Boolean wineoil;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @Column(nullable=false)
    private LocalDateTime updatedAt;

    public FastingType(){}

    public FastingType(
                        String code,
                        String name,
                        Translation nameTranslation,
                        String description,
                        Translation descriptionTranslation,
                        String comment,
                        Translation commentTranslation,
                        Boolean meat,
                        Boolean dairy,
                        Boolean fish,
                        Boolean wineoil
                        ){
        this.id =id;
        this.code = code;
        this.name = name;
        this.nameTranslation = nameTranslation;
        this.description = description;
        this.descriptionTranslation = descriptionTranslation;
        this.comment = comment;
        this.commentTranslation = commentTranslation;
        this.meat= meat;
        this.dairy = dairy;
        this.fish = fish;
        this.wineoil = wineoil;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Translation getNameTranslation(){
        return nameTranslation;
    }

    public void setNameTranslation(Translation nameTranslation){
        this.nameTranslation = nameTranslation;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Translation getDescriptionTranslation(){
        return descriptionTranslation;
    }

    public void setDescriptionTranslation(Translation descriptionTranslation){
        this.descriptionTranslation = descriptionTranslation;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public Translation getCommentTranslation(){
        return commentTranslation;
    }

    public void setCommentTranslation(Translation commentTranslation){
        this.commentTranslation = commentTranslation;
    }
    
    public Boolean getMeat(){
        return meat;
    }

    public void setMeat(Boolean meat){
        this.meat = meat;
    }

    public Boolean getDairy(){
        return dairy;
    }

    public void setDairy(Boolean dairy){
        this.dairy = dairy;
    }

    public Boolean getFish(){
        return fish;
    }

    public void setFish(Boolean fish){
        this.fish = fish;
    }

    public Boolean getWineoil(){
        return wineoil;
    }

    public void setWineoil(Boolean wineoil){
        this.wineoil = wineoil;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }
}
