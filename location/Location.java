package location;

import character.player.Player;

public abstract class Location {
    private Player player;
    private String name;

    Location(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    public abstract boolean onLocation();

    public Player getPlayer() {
        return player;
    }

    public String getPlayerUserName() {
        return player.getUsername();
    }

    public int getPlayerWealth(){
        return player.getWealth();
    }


    public String getPlayerName(){
        return player.getName();
    }

    public String getPlayerWeaponName() {
        return player.getWeaponName();
    }

    public int getPlayerDamage() {
        return player.getDamage();
    }

    int getPlayerHealth() {
        return player.getHealth();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}