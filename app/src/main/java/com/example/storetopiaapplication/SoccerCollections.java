package com.example.storetopiaapplication;

public class SoccerCollections {
    private String playerName;
    private String datePurchased;
    private String playerTeam;

    public SoccerCollections(String playerName, String datePurchased, String playerTeam) {
        this.playerName = playerName;
        this.datePurchased = datePurchased;
        this.playerTeam = playerTeam;
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

    public String getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerPrice(Double playerPrice) {
        this.playerTeam = playerTeam;
    }

    public SoccerCollections() {

    }
}
