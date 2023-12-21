package com.we.UniGather.controllers;

import com.we.UniGather.models.Activity;
import com.we.UniGather.repositories.UserActivityRepository;
import com.we.UniGather.services.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userActivities")
public class UserActivityController {

    private final UserActivityRepository userActivityRepository;
    private final UserActivityService userActivityService;

    @Autowired
    public UserActivityController(UserActivityRepository userActivityRepository, UserActivityService userActivityService) {
        this.userActivityRepository = userActivityRepository;
        this.userActivityService = userActivityService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Activity>> getUserPostedActivities(@PathVariable("userId") Long userId) {
        List<Activity> userPostedActivities = userActivityService.findActivitiesByUserId(userId);
        return new ResponseEntity<>(userPostedActivities, HttpStatus.OK);
    }
}
