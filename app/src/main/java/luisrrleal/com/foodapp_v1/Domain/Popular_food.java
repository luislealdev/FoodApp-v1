package luisrrleal.com.foodapp_v1.Domain;

import android.app.Fragment;

import java.io.Serializable;
//Clase que sirve como una interfaz

public class Popular_food implements Serializable {
    private String title;
    private String description;
    private String picUrl;
    private double price;
    private int numberinCart;
    //private int time;

    public Popular_food(String title, String description, String picUrl, double price) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberinCart() {
        return numberinCart;
    }

    public void setNumberinCart(int numberinCart) {
        this.numberinCart = numberinCart;
    }
}
