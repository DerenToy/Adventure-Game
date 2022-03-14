package character.player;

import item.Inventory;

import java.util.Scanner;

public class Player {
    private String weaponName;
    private String armorName;
    private int damage;
    private int health;
    private int wealth;
    //  private int wealth;
    private String charName;
    private String userName;
    private String name;
    private Inventory inventory = new Inventory();
    private Scanner input = new Scanner(System.in);

    public Player(String userName) {
        this.userName = userName;
        this.weaponName = "Beat";
        this.armorName = "No Shield";
    }

    public void selectChar() {

        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("Characters:");
        System.out.println("=========================================================================");
        for (GameChar list : charList) {
            System.out.println("Id:" + list.getId() + "\t" +
                    "Character: " + list.getName() + "\t" +
                    "Damage: " + list.getDamage() + "\t" +
                    "Health: " + list.getHealth() + "\t" +
                    "Money: " + list.getMoney());
        }
        System.out.println("=========================================================================");

        System.out.print("Please type your favorite character ID: ");
        int selectedChar = input.nextInt();

        switch (selectedChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
    }

/*
    public void printPlayerInfo() {
        System.out.println("##########################");
        System.out.println("Your character's properties:");
        System.out.println("Character Type:" + this.getCharName());
        System.out.println("Weapon:" + this.getInventory().getWeapon().getName());
        System.out.println("Weapon's Damage: " + this.getInventory().getWeapon().getDamage());
        System.out.println("Armor:" + this.getInventory().getArmor().getName());
        System.out.println("Damage: " + this.getDamage());
        System.out.println("Health: " + this.getHealth());
        System.out.println("Money: " + this.getMoney());
        System.out.println("##########################");

    }*/

    public void initPlayer(GameChar gameChar) {
        this.setCharName(gameChar.getName());
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());

    }
    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getArmorName() {
        return armorName;
    }

    public void setArmorName(String armorName) {
        this.armorName = armorName;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getWealth() {
        return wealth;
    }
    public void setWealth(int money) {
        this.wealth = money;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean getInventoryFirewoodCon() {
        return inventory.getFirewoodCon();
    }

    public boolean getInventoryFoodCon() {
        return inventory.getFoodCon();
    }

    public boolean getInventoryWaterCon() {
        return inventory.getWaterCon();
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public void printLocationInfo() {
            System.out.println();
            System.out.println("------------Places-----------------");
            System.out.println("0- Quit Game");
            System.out.println("1- Safe House");
            System.out.println("2- Tool Store");
            System.out.println("3- Forest");
            System.out.println("4- Cave");
            System.out.println("5- River");
            System.out.println("6- Mine");
    }
}
