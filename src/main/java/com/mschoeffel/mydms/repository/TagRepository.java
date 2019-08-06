package com.mschoeffel.mydms.repository;

import com.mschoeffel.mydms.model.Tag;
import com.mschoeffel.mydms.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
}
