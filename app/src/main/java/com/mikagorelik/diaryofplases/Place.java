package com.mikagorelik.diaryofplases;

import java.io.Serializable;
import java.util.Arrays;

public class Place implements Serializable {

    private String placeName;
    private String placeComment;
    private byte[] placeImage;
    private String placeLink;
    private String placeGPS;

    public Place(String placeName, String placeComment, byte[] placeImage, String placeLink, String placeGPS) {
        this.placeName = placeName;
        this.placeComment = placeComment;
        this.placeImage = placeImage;
        this.placeLink = placeLink;
        this.placeGPS = placeGPS;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceComment() {
        return placeComment;
    }

    public void setPlaceComment(String placeComment) {
        this.placeComment = placeComment;
    }

    public byte[] getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(byte[] placeImage) {
        this.placeImage = placeImage;
    }

    public String getPlaceLink() {
        return placeLink;
    }

    public void setPlaceLink(String placeLink) {
        this.placeLink = placeLink;
    }

    public String getPlaceGPS() {
        return placeGPS;
    }

    public void setPlaceGPS(String placeGPS) {
        this.placeGPS = placeGPS;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeName='" + placeName + '\'' +
                ", placeComment='" + placeComment + '\'' +
                ", placeImage=" + Arrays.toString(placeImage) +
                ", placeLink='" + placeLink + '\'' +
                ", placeGPS='" + placeGPS + '\'' +
                '}';
    }
}
