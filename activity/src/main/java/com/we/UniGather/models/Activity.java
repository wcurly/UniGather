package com.we.UniGather.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "location")
    private String location;

    @Column(name = "time")
    private String time;

    @ElementCollection
    @CollectionTable(name = "activity_images", joinColumns = @JoinColumn(name = "activity_id"))
    @Column(name = "image_path")
    private List<String> images;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "poster")
    private String poster;

    // Constructors (default and parameterized)

    public Activity() {
        // Default constructor
    }

    public Activity(String title, String url, String location, String time, List<String> images, String description, String type, String poster) {
        this.title = title;
        this.url = url;
        this.location = location;
        this.time = time;
        this.images = images;
        this.description = description;
        this.type = type;
        this.poster = poster;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getImage() {
        return images;
    }

    public void setImage(List<String> image) {
        this.images = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    // toString() method for debugging or logging

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url +'\'' +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", image='" + images + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type +'\'' +
                ", poster='" + poster +"\'" +
                '}';
    }

}
