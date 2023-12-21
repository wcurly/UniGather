package com.we.UniGather.controllers;
import com.we.UniGather.models.Activity;
import com.we.UniGather.repositories.ActivityRepository;
import com.we.UniGather.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityRepository activityRepository;
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityRepository activityRepository, ActivityService activityService) {
        this.activityRepository = activityRepository;
        this.activityService = activityService;
    }

    @PostMapping("/create")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity, @RequestParam("userId") Long userId) {
        try {
            Activity createdActivity = activityService.createActivity(activity, userId);
            return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable("id") Long id) {
        Optional<Activity> activityData = activityRepository.findById(id);

        if (activityData.isPresent()) {
            return new ResponseEntity<>(activityData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
