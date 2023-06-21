package com.spring.repo;

import com.spring.entity.WikiMedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaRepo extends JpaRepository<WikiMedia,Long> {
}
