package com.we.UniGather.controllers;
import com.we.UniGather.models.Activity;
import com.we.UniGather.models.Collection;
import com.we.UniGather.repositories.CollectionRepository;
import com.we.UniGather.services.CollectionService;
import org.aspectj.weaver.GeneratedReferenceTypeDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Activity>> getUserCollections(@PathVariable("userId") Long userId) {
        List<Activity> userCollections = collectionService.findActivitiesByUserId(userId);
        return new ResponseEntity<>(userCollections, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCollection(@RequestBody Collection collection) {
        try {
            String message = collectionService.addCollection(collection);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeCollection(@RequestBody Collection collection) {
        try {
            String message = collectionService.removeCollection(collection);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
