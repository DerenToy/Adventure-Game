package location;

import character.obstacle.Vampire;
import character.player.Player;

public class Forest extends BattleLocation {
    public Forest(Player player) {
        super(player, "Forest", new Vampire(), "Firewood");
    }
}
