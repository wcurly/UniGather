package com.we.UniGather.services;

import com.we.UniGather.models.Activity;
import com.we.UniGather.models.UserActivity;
import com.we.UniGather.repositories.ActivityRepository;
import com.we.UniGather.repositories.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserActivityRepository userActivityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, UserActivityRepository userActivityRepository) {
        this.activityRepository = activityRepository;
        this.userActivityRepository = userActivityRepository;
    }

    public Activity createActivity(Activity activity, Long userId) {
        Activity newActivity = activityRepository.save(activity);

        // 创建 UserActivity 实体并保存到数据库中
        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(userId);
        userActivity.setActivityId(newActivity.getId()); // 使用新活动的 ID
        userActivityRepository.save(userActivity);

        return newActivity;
    }
}

