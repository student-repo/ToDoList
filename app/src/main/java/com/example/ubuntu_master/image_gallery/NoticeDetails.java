package com.example.ubuntu_master.image_gallery;


import java.util.GregorianCalendar;

public class NoticeDetails {
    private String title;
    private String details;
    private GregorianCalendar date;
    private int id;
    private Boolean withoutHours;
    private Boolean finished;

    public Boolean getFinished() {
        return finished;
    }


    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public NoticeDetails(String title, String details, GregorianCalendar date, int id, Boolean withoutHours, Boolean finished) {
        this.title = title;
        this.details = details;
        this.date = date;
        this.id = id;
        this.withoutHours = withoutHours;
        this.finished = finished;
    }


    public Boolean getWithoutHours() {
        return withoutHours;
    }

    public void setWithoutHours(Boolean withoutHours) {
        this.withoutHours = withoutHours;
    }


    public NoticeDetails(String title, String details, GregorianCalendar date, int id, Boolean withoutHours) {
        this.withoutHours = withoutHours;
        this.finished = false;
        this.id = id;
        this.date = date;
        this.details = details;
        this.title = title;
    }

    public NoticeDetails(String title, String details, GregorianCalendar date, int id) {
        this.title = title;
        this.details = details;
        this.date = date;
        this.id = id;
        this.withoutHours = false;
        this.finished = false;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public String getTitle() {
        return title;
    }
    public int getId() {
        return id;
    }
}
