package com.ifigeneia.mooncalendar.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;

    private String code;

    private Long document_category_id;

    private String name;

    private Long name_translation_id;

    private String content;

    private Long content_translation_id;

    private String description;

    private Long description_translation_id;

    private String comment;

    private Long comment_translation_id;
}
