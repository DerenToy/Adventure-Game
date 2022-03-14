package location;

import character.obstacle.Obstacle;
import character.player.Player;
import item.Armor;
import item.Weapon;

import java.util.Objects;
import java.util.Scanner;

public abstract class BattleLocation extends Location {
    private Scanner input = new Scanner(System.in);

    private Obstacle obstacle;
    private String inventory;

    BattleLocation(Player player, String name, Obstacle o, String inventory) {
        super(player, name);
        this.obstacle = o;
        this.inventory = inventory;
    }

    @Override
    public boolean onLocation() {
        switch (super.getName()) {
            case "Forest":
                if (getPlayer().getInventoryFirewoodCon()) {
                    printMessage(super.getName());
                    return true;
                }
                break;
            case "Cave":
                if (getPlayer().getInventoryFoodCon()) {
                    printMessage(super.getName());
                    return true;
                }
                break;
            case "River":
                if (getPlayer().getInventoryWaterCon()) {
                    printMessage(super.getName());
                    return true;
                }
                break;
        }

        System.out.println("You are in " + super.getName());
        System.out.println("Hey, be careful and sure of being well-prepared. Here is " + this.getObstacleName() + "'s place!!!");
        if (!whatToDo()) {
            return false;
        }
        System.out.println();
        System.out.println("You are on the main menu. You can make a choice up to where you would like to enter.");
        return true;
    }

    private void printMessage(String name) {
        System.out.println("You have already passed " + name + ". You can not enter here!!!");
    }

    private boolean whatToDo() {
        System.out.println("Fight or Leave?\n\nPress 1 to fight or Press 0 to leave");
        int whatToDo = input.nextInt();

        switch (whatToDo) {
            case 0:
                System.out.println("You have left the " + super.getName());
                break;
            case 1:
                System.out.println("It is time to attack right now! ATTACK!");
                return combat();
            default:
                System.out.println(super.getPlayerUserName() + ", please respecify what you would like to do...");
                whatToDo();
                break;
        }
        return true;
    }

    private boolean combat() {
        int numberObstacle = Obstacle.obstacleNumber(this.getObstacleId());
        System.out.println("Nooo! " + numberObstacle + " " + this.getObstacleName() + "s are present in the " + super.getName());

        String keyboard;
        int obstacleRemDamage, playerRemDamage;
        int defaultObstacleHealth = this.getObstacleHealth();

        int obstacleDamage = this.getObstacleDamage();
        Obstacle obstacle = this.getObstacle();
        if (super.getName().equals("Mine")) {
            int[] snakeDemArr = {3, 4, 5, 6};
            int idx = (int) (Math.random() * 4);
            obstacle.setDamage(snakeDemArr[idx]);
            System.out.println("Snake Damage: " + obstacleDamage);
        }

        double probability = Math.random();
        int whichSideStart = probability >= 0.5 ? 1 : 0;

        if (whichSideStart == 1) {
            System.out.println("You are starting the round.");
        } else {
            System.out.println(obstacle.getName() + " is starting the round.");
        }

        boolean exitLoop = false;

        Player player = super.getPlayer();
        for (int i = 0; i < numberObstacle; i++) {
            while (0 < this.getPlayer().getHealth() && 0 < obstacle.getHealth()) {
                int playerHealth = super.getPlayerHealth();
                if (whichSideStart == 1) {
                    System.out.println("Press A to attack or Press R to run away.");
                    keyboard = input.next().toUpperCase();

                    if (keyboard.equals("A")) {
                        System.out.println("You have attacked.");
                        obstacleRemDamage = obstacle.getHealth() - this.getPlayer().getDamage();
                        obstacle.setHealth(obstacleRemDamage);
                    }

                    System.out.println("If you want to run away, Press R.");
                    keyboard = input.next().toUpperCase();
                    if (keyboard.equals("R")) {
                        exitLoop = true;
                        break;
                    }

                    if (0 < this.getObstacleHealth()) {
                        System.out.println(this.getObstacleName() + " has attacked you. It's your turn now.");
                        playerRemDamage = playerHealth - obstacleDamage;
                        player.setHealth(playerRemDamage);
                    }

                } else {
                    if (0 < playerHealth) {
                        playerRemDamage = playerHealth - obstacleDamage;
                        player.setHealth(playerRemDamage);
                        System.out.println(this.getObstacleName() + " has attacked you. It's your turn now.");

                        System.out.println("Press A to attack or Press R to run away.");
                        keyboard = input.next().toUpperCase();
                        if (keyboard.equals("A")) {
                            obstacleRemDamage = this.getObstacleHealth() - player.getDamage();
                            obstacle.setHealth(obstacleRemDamage);
                        }
                        if (keyboard.equals("R")) {
                            exitLoop = true;
                            break;
                        }

                    }
                }

                if (obstacle.getHealth() <= 0) {
                    System.out.println("Your Health: " + playerHealth + " " + obstacle.getName() + "'s Health: 0");
                } else {
                    if (playerHealth <= 0) {
                        System.out.println("Your Health: 0 " + obstacle.getName() + "'s Health: " + obstacle.getHealth());
                    } else {
                        System.out.println("Your Health: " + playerHealth + " " + obstacle.getName() + "'s Health: " + obstacle.getHealth());
                    }
                }
            }

            if (exitLoop) {
                break;
            }

            if (obstacle.getHealth() <= 0) {
                System.out.println("You have killed " + (i + 1) + ". " + obstacle.getName());
                setWealthLoc();
                System.out.println("Your Health: " + this.getPlayer().getHealth() + " Your Wealth: " + this.getPlayer().getWealth());
                System.out.println();
                obstacle.setHealth(defaultObstacleHealth);
            }
        }

        if (exitLoop) {
            return true;
        }

        if (player.getHealth() <= 0) {
            System.out.println("You've died...");
            return false;
        }

        System.out.println("You've killed all " + obstacle.getName() + "s");

        switch (super.getName()) {
            case "Forest":
                player.getInventory().setFirewoodCon(true);
                player.getInventory().setFirewood("Firewood(acquired)");

                break;
            case "Cave":
                player.getInventory().setFoodCon(true);
                player.getInventory().setFood("Food(acquired)");

                break;
            case "River":
                player.getInventory().setWaterCon(true);
                player.getInventory().setWater("Water(acquired)");
                break;
        }

        System.out.println("Character: " + player.getName() + "\tWeapon: " + player.getWeaponName() +
                "\tArmor: " + player.getArmorName() + "\tDamage: " + player.getDamage() +
                "\tHealth: " + player.getHealth() + "\tWealth: " + player.getWealth() +
                "\t\tInventory: food-" + (player.getInventory().getFood()) +
                " water-" + (player.getInventory().getWater()) + " firewood-" +
                (player.getInventory().getFirewood()) +
                "Bonus Armor: " + (player.getInventory().getBonusArmor()));

        return true;
    }

    public void setWealthLoc() {
        Player player = super.getPlayer();
        if (this.getObstacle().getId() == 4) {
            double chanceMajor = Math.random();
            if (chanceMajor < 0.15) {
                System.out.println("You have got a chance to get a weapon.");
                double chanceMinor = Math.random();

                String weaponList = super.getPlayerWeaponName() + ",";

                if (chanceMinor < 0.2) {
                    System.out.println("You have got a chance to get a rifle.");
                    player.setWeaponName(weaponList + "Rifle");
                    int totalDamage = player.getDamage() + Objects.requireNonNull(Weapon.selectWeapon(3)).getDamage();
                    player.setDamage(totalDamage);

                } else if (chanceMinor < 0.5) {
                    System.out.println("You have got a chance to get a sword.");
                    player.setWeaponName(weaponList + "Sword");
                    int totalDamage = super.getPlayerDamage() + Objects.requireNonNull(Weapon.selectWeapon(2)).getDamage();
                    player.setDamage(totalDamage);

                } else {
                    System.out.println("You've got a chance to get a pistol.");
                    player.setWeaponName(weaponList + "Pistol");
                    int totalDamage = super.getPlayerDamage() + Objects.requireNonNull(Weapon.selectWeapon(1)).getDamage();
                    player.setDamage(totalDamage);
                }

            } else if (chanceMajor < 0.30) {
                System.out.println("You have got a chance to get an armor.");
                double chanceMinor = Math.random();

                if (chanceMinor < 0.2) {
                    System.out.println("You have got a chance to get a light armor.");
                    player.setArmorName("Light");
                    int totalHealth = player.getHealth() + Objects.requireNonNull(Armor.selectArmor(1)).getShield();
                    player.setHealth(totalHealth);
                    player.getInventory().setBonusArmor("Acquired (Light)");
                    player.getInventory().setBonusArmorCon(true);

                } else if (chanceMinor < 0.5) {
                    System.out.println("You've got a chance to get a medium armor.");
                    player.setArmorName("Medium");
                    int totalHealth = player.getHealth() + Objects.requireNonNull(Armor.selectArmor(2)).getShield();
                    player.setHealth(totalHealth);
                    player.getInventory().setBonusArmor("Acquired (Medium)");
                    player.getInventory().setBonusArmorCon(true);

                } else {
                    System.out.println("You have got a chance to get a heavy armor.");
                    player.setArmorName("Heavy");
                    int totalHealth = player.getHealth() + Objects.requireNonNull(Armor.selectArmor(3)).getShield();
                    player.setHealth(totalHealth);
                    player.getInventory().setBonusArmor("Acquired (Heavy)");
                    player.getInventory().setBonusArmorCon(true);
                }

            } else if (chanceMajor < 0.55) {
                System.out.println("You have got a chance to get an award.");
                double chanceMinor = Math.random();
                if (chanceMinor < 0.2) {
                    System.out.println("You have got a chance to get an award 10.");
                    int totalWealth = player.getWealth() + 10;
                    player.setWealth(totalWealth);

                } else if (chanceMinor < 0.5) {
                    System.out.println("You have got a chance to get an award 5.");
                    int totalWealth = player.getWealth() + 5;
                    player.setWealth(totalWealth);

                } else {
                    System.out.println("You have got a chance to get an award 1.");
                    int totalWealth = player.getWealth() + 1;
                    player.setWealth(totalWealth);
                }

            } else {
                System.out.println("OPPPS... Nothing has come out!");
            }

        } else {
            int finalWealth = player.getWealth() + this.getObstacle().getAward();
            player.setWealth(finalWealth);
        }
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    private int getObstacleId() {
        return obstacle.getId();
    }

    private String getObstacleName() {
        return obstacle.getName();
    }

    private int getObstacleHealth() {
        return obstacle.getHealth();
    }

    private int getObstacleDamage() {
        return obstacle.getDamage();
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }
}
