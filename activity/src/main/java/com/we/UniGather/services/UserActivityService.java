package com.we.UniGather.services;

import com.we.UniGather.models.Activity;
import com.we.UniGather.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActivityService {

    private final ActivityRepository activityRepository;

    public UserActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }


    public List<Activity> findActivitiesByUserId(Long userId) {
        List<Long> activityIds = activityRepository.findActivityIdsByUserId2(userId);
        return activityRepository.findByIdIn(activityIds);
    }
}
