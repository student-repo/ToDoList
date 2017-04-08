package com.example.ubuntu_master.image_gallery;


public class ImageInfo {
    private String title;
    private String description;
    private String image;
    private int progress;
    private int id;


    public ImageInfo(String title, String description, String image, int progress, int id) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.progress = progress;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getProgress() {
        return progress;
    }

    public int getId() {
        return id;
    }
    public void setProgress(int progress){
        this.progress = progress;
    }
}
