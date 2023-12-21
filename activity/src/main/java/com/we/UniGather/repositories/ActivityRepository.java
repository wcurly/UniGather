package com.we.UniGather.repositories;

import com.we.UniGather.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("SELECT c.activityId FROM Collection c WHERE c.userId = ?1")
    List<Long> findActivityIdsByUserId(Long userId);

    List<Activity> findByIdIn(List<Long> activityIds);
}
