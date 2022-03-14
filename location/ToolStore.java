package location;

import character.player.Player;
import item.Armor;
import item.Weapon;

import java.util.Objects;
import java.util.Scanner;

public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    private Scanner input = new Scanner(System.in);

    @Override
    public boolean onLocation() {
        System.out.println();
        System.out.println("*************** Welcome To Tool Store".toUpperCase() + " **********************");
        System.out.println("Welcome " + this.getPlayerUserName() + "... I'm your weapon provider. What would you like to purchase?");
        System.out.println();
        System.out.println("---------- Weapons ------------");
        Weapon.showWeapons();
        System.out.println();
        System.out.println("---------- Armors ------------");
        Armor.showArmors();
        System.out.println();
        System.out.println("Your wealth: " + this.getPlayerWealth());
        System.out.println();
        selectionApi();
        System.out.println();

        System.out.println("Character: " + this.getPlayerName() + "\tWeapon: " + this.getPlayerWeaponName() +
                "\tArmor: " + this.getPlayer().getArmorName() + "\tDamage: " + this.getPlayerDamage() +
                "\tHealth: " + this.getPlayerHealth() + "\tWealth: " + this.getPlayerWealth() +
                "\t\tInventory: food-" + (this.getPlayer().getInventory().getFood()) +
                " water-" + (this.getPlayer().getInventory().getWater()) +
                " firewood-" + (this.getPlayer().getInventory().getFirewood()));

        System.out.println();
        System.out.println("See you soon, " + this.getPlayerUserName());
        return true;
    }

    public void selectionApi() {
        System.out.println("You can purchase a weapon or an armor.");
        System.out.println("If you would like to leave the tool store, please press 0.");
        System.out.println("If you would like to purchase a weapon, please press 1.");
        System.out.println("If you would like to purchase an armor, please press 2.");
        System.out.print("Your prefer: ");
        int selectButton = input.nextInt();
        System.out.println();

        switch (selectButton) {
            case 1:
                buyWeapon();
                selectionApi();
                break;
            case 2:
                buyArmor();
                selectionApi();
                break;
            case 0:

                break;
            default:
                System.out.println("We don't have this kind of an option, " + this.getPlayer().getUsername() + ". Please, Try Again!");
                System.out.println();
                selectionApi();
        }
    }

    public void buyWeapon() {
        System.out.println("If you would like to purchase a weapon, please select one of them...");
        System.out.println("If you like to quit, you can press the number 0");
        System.out.print("Your prefer: ");
        int selectWeapon = input.nextInt();
        System.out.println();
        Weapon weapon = Weapon.selectWeapon(selectWeapon);
        int newDamage, rem;
        String weaponList = this.getPlayer().getWeaponName() + ",";
        System.out.println(weaponList);

        switch (selectWeapon) {
            case 1:
                System.out.println("You've selected a pistol!");
                if (Objects.requireNonNull(weapon).getPrice() > this.getPlayer().getWealth()) {
                    System.out.println("You can not afford to purchase a pistol!!!");
                    buyWeapon();
                } else {
                    System.out.println("You have purchased a pistol.");
                    this.getPlayer().setWeaponName(weaponList + weapon.getName());

                    newDamage = this.getPlayer().getDamage() + weapon.getDamage();
                    this.getPlayer().setDamage(newDamage);
                    System.out.println("Your Damage: " + this.getPlayer().getDamage());
                    rem = this.getPlayer().getWealth() - weapon.getPrice();
                    this.getPlayer().setWealth(rem);
                    System.out.println("Your remaining wealth: " + this.getPlayer().getWealth());
                    this.getPlayer().setWeaponName(weapon.getName());
                    buyWeapon();
                }
                break;
            case 2:
                System.out.println("You have selected a sword!");
                if (Objects.requireNonNull(weapon).getPrice() > this.getPlayer().getWealth()) {
                    System.out.println("You can not afford to purchase a sword!!!");
                    buyWeapon();
                } else {
                    System.out.println("You've purchased a sword.");
                    this.getPlayer().setWeaponName(weaponList + weapon.getName());
                    newDamage = this.getPlayerDamage() + weapon.getDamage();
                    this.getPlayer().setDamage(newDamage);
                    System.out.println("Your Damage: " + this.getPlayerDamage());
                    rem = this.getPlayer().getWealth() - weapon.getPrice();
                    this.getPlayer().setWealth(rem);
                    System.out.println("Your remaining  wealth: " + this.getPlayerWealth());
                    buyWeapon();
                }
                break;
            case 3:
                System.out.println("You've selected a rifle!");
                if (Objects.requireNonNull(weapon).getPrice() > this.getPlayer().getWealth()) {
                    System.out.println("You can not afford to purchase a rifle!!!");
                    buyWeapon();
                } else {
                    System.out.println("You've purchased a rifle.");
                    this.getPlayer().setWeaponName(weaponList + weapon.getName());
                    newDamage = this.getPlayerDamage() + weapon.getDamage();
                    this.getPlayer().setDamage(newDamage);
                    System.out.println("Your Damage: " + this.getPlayer().getDamage());
                    rem = this.getPlayer().getWealth() - weapon.getPrice();
                    this.getPlayer().setWealth(rem);
                    System.out.println("Your remaining  wealth: " + this.getPlayerWealth());
                    buyWeapon();
                }
                break;
            case 0:
                break;
            default:
                System.out.println("We don't have this kind of a weapon, " + this.getPlayer().getUsername() + ". Please, Try Again!");
                System.out.println();
                buyWeapon();
        }
    }

    public void buyArmor() {
        System.out.println("If you would like to purchase a armor, please select one of them...");
        System.out.println("If you like to quit, you can press the number 0");
        System.out.print("Your prefer: ");
        int slctArmor = input.nextInt();
        System.out.println();
        Armor armor = Armor.selectArmor(slctArmor);
        int newHealth, rem;

        switch (slctArmor) {
            case 1:
                System.out.println("You've selected a light armor!");
                if (Objects.requireNonNull(armor).getPrice() > this.getPlayer().getWealth()) {
                    System.out.println("You can not afford to purchase a light armor!!!");
                    buyArmor();
                } else {
                    System.out.println("You've purchased a light armor.");
                    this.getPlayer().setArmorName(armor.getName());
                    newHealth = this.getPlayerHealth() + armor.getShield();
                    this.getPlayer().setHealth(newHealth);
                    System.out.println("Your Health: " + this.getPlayerHealth());
                    rem = this.getPlayerWealth() - armor.getPrice();
                    this.getPlayer().setWealth(rem);
                    System.out.println("Your remaining  wealth: " + this.getPlayerWealth());
                    buyArmor();
                }
                break;
            case 2:
                System.out.println("You have selected a medium armor!");
                if (Objects.requireNonNull(armor).getPrice() > this.getPlayer().getWealth()) {
                    System.out.println("You can not afford to purchase a sword!!!");
                    buyArmor();
                } else {
                    System.out.println("You have purchased a medium armor.");
                    this.getPlayer().setArmorName(armor.getName());
                    newHealth = this.getPlayer().getHealth() + armor.getShield();
                    this.getPlayer().setHealth(newHealth);
                    System.out.println("Your Health: " + this.getPlayer().getHealth());
                    rem = this.getPlayer().getWealth() - armor.getPrice();
                    this.getPlayer().setWealth(rem);
                    System.out.println("Your remaining  wealth: " + this.getPlayer().getWealth());
                    buyArmor();
                }
                break;
            case 3:
                System.out.println("You've selected a heavy armor!");
                if (Objects.requireNonNull(armor).getPrice() > this.getPlayer().getWealth()) {
                    System.out.println("You can not afford to purchase a heavy armor!!!");
                    buyArmor();
                } else {
                    System.out.println("You've purchased a heavy armor.");
                    this.getPlayer().setArmorName(armor.getName());
                    newHealth = this.getPlayer().getWealth() + armor.getShield();
                    this.getPlayer().setHealth(newHealth);
                    System.out.println("Your Health: " + this.getPlayer().getHealth());
                    rem = this.getPlayer().getWealth() - armor.getPrice();
                    this.getPlayer().setWealth(rem);
                    System.out.println("Your remaining  wealth: " + this.getPlayer().getWealth());
                    buyArmor();
                }
                break;
            case 0:
                break;
            default:
                System.out.println("We don't have this kind of a armor, " + this.getPlayer().getUsername() + ". Please, Try Again!");
                System.out.println();
                buyArmor();
        }
    }
}