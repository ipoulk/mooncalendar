package com.ifigeneia.mooncalendar.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="event_category")
public class EventCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="parent_category_id")
    private EventCategory parentCategoryId;

    @Column(nullable=false,unique = true)
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

    @Column(name="created_at",nullable=false)
    private LocalDateTime createdAt;
    @Column(name="updated_at",nullable=false)
    private LocalDateTime updatedAt;

    protected EventCategory(){}

    public EventCategory(
                        EventCategory parentCategoryId,
                        String code,
                        String name,
                        Translation nameTranslation,
                        String description,
                        Translation descriptionTranslation,
                        String comment,
                        Translation commentTranslation
                        ){
        this.parentCategoryId = parentCategoryId;
        this.code = code;
        this.name = name;
        this.nameTranslation= nameTranslation;
        this.description = description;
        this.descriptionTranslation = descriptionTranslation;
        this.comment = comment;
        this.commentTranslation= commentTranslation;

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public EventCategory getParentCategoryId(){
        return parentCategoryId;
    }

    public void setParentCategoryId(EventCategory parentCategoryId){
        this.parentCategoryId = parentCategoryId;
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

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }


}
