package com.ifigeneia.mooncalendar.repository;

import com.ifigeneia.mooncalendar.persistence.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranslationRepository extends JpaRepository<Translation, Long> {

}
