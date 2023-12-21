package com.we.UniGather.repositories;

import com.we.UniGather.models.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    boolean existsByUserIdAndActivityId(Long userId, Long activityId);

    void deleteByUserIdAndActivityId(Long userId, Long activityId);
}