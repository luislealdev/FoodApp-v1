package luisrrleal.com.foodapp_v1.Domain;

import android.graphics.Bitmap;

//Una clase que sirve como base para proveer a los adapters de una estructura de datos requerida para los items que se 
//renderizen en los distintos RecyclerViews

//Los constructores cambian dependiendo de la información que vaya a a requerir un layout/item de un RecyclerView
public class Data_Provider {
    String cardTitle;
    String cardPrice;
    String cardDescription;

    int numberInCart;
    int cardImgResource;

    //Estuve tratando de implementra un dato Bitmap para renderizar las imágenes que consumo desde el Storage de Firebase,
    //pero me da problemas ya que no me renderiza nada aún, probablemente no se quede
    Bitmap cardImgResource2;

    //Constructor for the Food cards of the RecyclerView
    public Data_Provider(String cardTitle, String cardPrice, int cardImgResource) {
        this.cardTitle = cardTitle;
        this.cardPrice = cardPrice;
        this.cardImgResource = cardImgResource;
    }
    public Data_Provider(String cardTitle, String cardPrice, Bitmap cardImgResource) {
        this.cardTitle = cardTitle;
        this.cardPrice = cardPrice;
        this.cardImgResource2 = cardImgResource;
    }

    //Constructor for the Food sections cards
    public Data_Provider(String cardTitle, int cardImgResource) {
        this.cardTitle = cardTitle;
        this.cardImgResource = cardImgResource;
    }

    //Constructor for the InfoFoodActivity
    public Data_Provider(String cardTitle, String cardPrice, String cardDescription, int numberInCart, int cardImgResource) {
        this.cardTitle = cardTitle;
        this.cardPrice = cardPrice;
        this.cardDescription = cardDescription;
        this.numberInCart = numberInCart;
        this.cardImgResource = cardImgResource;
    }

    //Methods for consuming or modifing the data
    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(String cardPrice) {
        this.cardPrice = cardPrice;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public int getCardImgResource() {
        return cardImgResource;
    }

    public void setCardImgResource(int cardImgResource) {
        this.cardImgResource = cardImgResource;
    }

    public Bitmap getCardImgResource2() {
        return cardImgResource2;
    }

    public void setCardImgResource2(Bitmap cardImgResource2) {
        this.cardImgResource2 = cardImgResource2;
    }
}

