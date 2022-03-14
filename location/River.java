package location;

import character.obstacle.Bear;
import character.player.Player;

public class River extends BattleLocation {
    public River(Player player) {
        super(player, "River", new Bear(), "Water");
    }
}
