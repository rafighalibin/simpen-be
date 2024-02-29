package com.nakahama.simpenbackend.User.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nakahama.simpenbackend.User.model.Tag;

@Repository
public interface TagDb extends JpaRepository<Tag, Long>{
    Tag findById(long id);
}
