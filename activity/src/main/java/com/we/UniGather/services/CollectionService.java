package com.we.UniGather.services;

import com.we.UniGather.models.Activity;
import com.we.UniGather.models.Collection;
import com.we.UniGather.repositories.ActivityRepository;
import com.we.UniGather.repositories.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {

    private final ActivityRepository activityRepository;
    private final CollectionRepository collectionRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository, ActivityRepository activityRepository) {
        this.collectionRepository = collectionRepository;
        this.activityRepository = activityRepository;
    }

    public List<Activity> findActivitiesByUserId(Long userId) {
        List<Long> activityIds = activityRepository.findActivityIdsByUserId(userId);
        return activityRepository.findByIdIn(activityIds);
    }

    public String addCollection(Collection collection) {
        // 检查是否已经收藏过该活动
        boolean isAlreadyCollected = collectionRepository.existsByUserIdAndActivityId(collection.getUserId(), collection.getActivityId());

        if (isAlreadyCollected) {
            return "Activity is already in user's collection";
        } else {
            collectionRepository.save(collection);
            return "Activity added to collection successfully";
        }
    }

    public String removeCollection(Collection collection) {
        // 检查是否存在该收藏记录
        boolean exists = collectionRepository.existsByUserIdAndActivityId(collection.getUserId(), collection.getActivityId());

        if (exists) {
            collectionRepository.deleteByUserIdAndActivityId(collection.getUserId(), collection.getActivityId());
            return "Activity removed from collection successfully";
        } else {
            return "Activity not found in user's collection";
        }
    }
}
