public class PlayerFactory {
    public PlayerFactory(){}
    public Player buildPlayer(String type){
        switch (type.toLowerCase()){
            case "human":
                return new HumanPlayer();
            case "whatever":
                return new WhateverPlayer();
            case "naive":
                return new NaivePlayer();
            case "smart":
                return new SmartPlayer();
            default:
                return null;
        }
    }
}