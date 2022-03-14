import character.player.Player;
import location.*;

import java.util.Objects;
import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to adventure game!");
        System.out.print("Please enter a name: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Welcome " + player.getUsername() + "!");
        player.selectChar();
        Location location = null;
        Location[] locations = {new SafeHouse(player), new ToolStore(player), new Cave(player), new Forest(player), new River(player), new Mine(player)};
        while (true) {
            player.printLocationInfo();
            System.out.print("Please select the location you want to go: ");
            int locNum = input.nextInt();
            boolean isQuit = false;

            switch (locNum) {
                case 0:
                    isQuit = true;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Forest(player);
                    break;
                case 4:
                    location = new Cave(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
            }

            if (isQuit) {
                System.out.println("You've quited the game. See you soon...");
                break;
            }

            if (!Objects.requireNonNull(location).onLocation()) {
                System.out.println("GAME OVER");
                break;
            }

            boolean forestCon = player.getInventory().getFirewoodCon();
            boolean caveCon = player.getInventory().getFoodCon();
            boolean riverCon = player.getInventory().getWaterCon();

            if (forestCon && caveCon && riverCon) {
                System.out.println("YOU WIN!!! You've completed the game...");
                break;
            }


        }
    }
}
