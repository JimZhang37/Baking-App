package com.example.bakingapp.data;

import com.squareup.moshi.Json;

public class Step {

    @Json(name = "id")
    private Integer id;
    @Json(name = "shortDescription")
    private String shortDescription;
    @Json(name = "description")
    private String description;
    @Json(name = "videoURL")
    private String videoURL;
    @Json(name = "thumbnailURL")
    private String thumbnailURL;

    /**
     * No args constructor for use in serialization
     *
     */
    public Step() {
    }

    /**
     *
     * @param videoURL
     * @param description
     * @param id
     * @param shortDescription
     * @param thumbnailURL
     */
    public Step(Integer id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        super();
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

}