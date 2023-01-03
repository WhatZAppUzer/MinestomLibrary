package dev.whatsappuser.minestom.lib.world;

import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;

/**
 * development by TimoH created on 15:29:48 | 30.12.2022
 */

public interface MinestomWorld extends World {

    void teleport(Player player);

    default Instance getInstance() {
        return (Instance) this;
    }
}
