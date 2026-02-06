package com.ifigeneia.mooncalendar.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="event_date_rule")
public class EventDateRule {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;

    @Column(unique=true)
    private String code;

    @ManyToOne
    @JoinColumn(name="date_rule_type_id",nullable = false)
    private DateRuleType dateRuleType;

    @ManyToOne
    @JoinColumn(name="fasting_type_id")
    private FastingType fastingType ;

    @ManyToOne
    @JoinColumn(name="event_id",nullable = false)
    private Event event ;

    @Column(name="start_month")
    private Integer startMonth;

    @Column(name="start_day")
    private Integer startDay;

    @Column(name="end_month")
    private Integer endMonth;

    @Column(name="end_day")
    private Integer endDay;

    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="description_translation_id",nullable=false)
    private Translation descriptionTranslation;

    private String comment;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="comment_translation_id",nullable=false)
    private Translation commentTranslation;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    protected EventDateRule(){}

    public EventDateRule(
                        String code,
                        DateRuleType dateRuleType,
                        FastingType fastingType,
                        Event event,
                        Integer startMonth,
                        Integer startDay,
                        Integer endMonth,
                        Integer endDay,
                        String description,
                        Translation descriptionTranslation,
                        String comment,
                        Translation commentTranslation){

        this.code = code;
        this.dateRuleType = dateRuleType;
        this.fastingType = fastingType;
        this.event = event;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.description = description;
        this.descriptionTranslation = descriptionTranslation;
        this.comment = comment;
        this.commentTranslation = commentTranslation;

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

    public DateRuleType getDateRuleType(){
        return dateRuleType;
    }

    public void setDateRuleType(DateRuleType dateRuleType){
        this.dateRuleType = dateRuleType;
    }

    public FastingType getFastingType(){
        return fastingType;
    }

    public void setFastingType(FastingType fastingType){
        this.fastingType = fastingType;
    }

    public Event getEvent(){
        return event;
    }

    public void setEvent(Event event){
        this.event = event;
    }

    public Integer getStartMonth(){
        return startMonth;
    }

    public void setStartMonth(Integer startMonth){
        this.startMonth = startMonth;
    }

    public Integer getStartDay(){
        return startDay;
    }

    public void setStartDay(Integer startDay){
        this.startDay = startDay;
    }

    public Integer getEndMonth(){
        return endMonth;
    }

    public void setEndMonth(Integer endMonth){
        this.endMonth = endMonth;
    }

    public Integer getEventDay(){
        return endDay;
    }

    public void setEndDay(Integer endDay){
        this.endDay = endDay;
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

    public void setComment(String description){
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
