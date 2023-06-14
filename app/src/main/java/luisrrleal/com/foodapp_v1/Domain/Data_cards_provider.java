package luisrrleal.com.foodapp_v1.Domain;

public class Data_cards_provider {

    private String cardTitle;
    private String cardPrice;
    private int imgResource;

    public Data_cards_provider(String cardTitle, String cardPrice, int imgResource) {
        this.cardTitle = cardTitle;
        this.cardPrice = cardPrice;
        this.imgResource = imgResource;
    }

    public Data_cards_provider(String cardTitle, int imgResource) {
        this.cardTitle = cardTitle;
        this.imgResource = imgResource;
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

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
