package location;

import character.obstacle.Zombie;
import character.player.Player;

public class Cave extends BattleLocation{
    public Cave(Player player) {
        super(player, "Cave", new Zombie(), "Food");
    }
}
