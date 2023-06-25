package luisrrleal.com.foodapp_v1.Domain;

public class Data_Provider {
    String cardTitle;
    String cardPrice;
    String cardDescription;

    int numberInCart;
    int cardImgResource;

    //Constructor for the Food cards of the RecyclerView
    public Data_Provider(String cardTitle, String cardPrice, int cardImgResource) {
        this.cardTitle = cardTitle;
        this.cardPrice = cardPrice;
        this.cardImgResource = cardImgResource;
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
}

