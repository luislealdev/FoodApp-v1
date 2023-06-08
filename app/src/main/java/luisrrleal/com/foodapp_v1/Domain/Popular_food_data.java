package luisrrleal.com.foodapp_v1.Domain;

import java.io.Serializable;
//Clase que sirve como una interfaz

public class Popular_food_data implements Serializable {
    private String title;
    private String description;
    private int picUrl;
    private double price;
    private int numberinCart;
    //private int time;

    public Popular_food_data(String title, String description, int picUrl, double price) {
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

    public int getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(int picUrl) {
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
