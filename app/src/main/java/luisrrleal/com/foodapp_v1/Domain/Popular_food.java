package luisrrleal.com.foodapp_v1.Domain;

//Class that provide data to the cards layout
public class Popular_food {
    private String title;
    private String description;
    private int picUrl;
    private String price;
    private int numberinCart;
    //private int time;

    public Popular_food(String title, String description, int picUrl, String price) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.price = price;
    }

    public Popular_food(String title, String price, int picUrl) {
        this.title = title;
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

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public int getNumberinCart() {
        return numberinCart;
    }
    public void setNumberinCart(int numberinCart) {
        this.numberinCart = numberinCart;
    }
}
