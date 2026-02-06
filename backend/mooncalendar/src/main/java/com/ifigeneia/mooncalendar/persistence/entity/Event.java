package com.ifigeneia.mooncalendar.persistence.entity;


import jakarta.persistence.*;

import java.time.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;

    @Column(nullable=false, unique = true)
    private String code;

    @Column(name="is_season")
    private Boolean isSeason;

    @ManyToOne
    @JoinColumn(name = "event_category_id", nullable = false )
    private EventCategory eventCategory ;

    @Column(nullable=false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_translation_id", nullable = false)
    private Translation nameTranslation ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "description_translation_id", nullable = false )
    private Translation descriptionTranslation ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_translation_id",nullable = false )
    private Translation commentTranslation ;

    private String description;

    private String comment;

    @Column(name="created_at",nullable=false)
    private LocalDateTime createdAt;

    @Column(name="updated_at",nullable=false)
    private LocalDateTime updatedAt;

    protected Event(){

    }

    public Event(
                String code,
                Boolean isSeason,
                EventCategory eventCategory,
                 String name,
                Translation nameTranslation,
                String description,
                 Translation descriptionTranslation,
                String comment,
                 Translation commentTranslation){
        this.code = code;
        this.isSeason = isSeason;
        this.eventCategory = eventCategory;
        this.name = name;
        this.nameTranslation = nameTranslation;
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

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }


    public Boolean getIsSeason(){
        return isSeason;
    }

    public void setIsSeason(Boolean isSeason){
        this.isSeason = isSeason;
    }

    public EventCategory getEventCategory(){
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory){
        this.eventCategory = eventCategory;
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

    @Override
    public String toString(){
        return name;
    }

}
