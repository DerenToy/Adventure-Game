package location;

import character.obstacle.Snake;
import character.player.Player;

public class Mine extends BattleLocation{
    public Mine(Player player) {
        super(player, "Mine", new Snake(), "Award/Weapon/Armor");
    }
}
