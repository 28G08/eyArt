package com.galuhsukma.bismillah;

public class Model {
    private byte[] image;
    private  String title, about;

    private int id, favorite;
    public Model(int id, byte[] image, String title, String about, int favorite) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.about = about;
        this.favorite = favorite;
    }

    //getter abdnd setter method

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
