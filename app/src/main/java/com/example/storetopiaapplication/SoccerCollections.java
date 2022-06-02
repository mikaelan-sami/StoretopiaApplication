package com.example.storetopiaapplication;

public class SoccerCollections {
    private String playerName;
    private String datePurchased;
    private Double playerPrice;

    public SoccerCollections(String playerName, String datePurchased, Double playerPrice) {
        this.playerName = playerName;
        this.datePurchased = datePurchased;
        this.playerPrice = playerPrice;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Double getPlayerPrice() {
        return playerPrice;
    }

    public void setPlayerPrice(Double playerPrice) {
        this.playerPrice = playerPrice;
    }

    public SoccerCollections() {

    }
}
