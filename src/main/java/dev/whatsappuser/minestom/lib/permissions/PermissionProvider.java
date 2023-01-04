package dev.whatsappuser.minestom.lib.permissions;

import lombok.Getter;
import net.minestom.server.entity.Player;

/**
 * development by TimoH created on 15:38:16 | 04.01.2023
 */

@Getter
public class PermissionProvider {

    private String extensionPermission;
    private int opLevel;

    public PermissionProvider(String extensionPermission) {
        this(4, extensionPermission);
    }

    public PermissionProvider(int opLevel, String extensionPermission) {
        this.extensionPermission = extensionPermission;
        this.opLevel = opLevel;
    }

    public void update(int opLevel, String extensionPermission) {
        this.extensionPermission = extensionPermission;
        this.opLevel = opLevel;
    }

    public void update(String extensionPermission) {
        this.extensionPermission = extensionPermission;
        this.opLevel = 4;
    }

    public boolean hasPermission(Player sender, String permission) {
        if(sender.hasPermission("*") || sender.getPermissionLevel() >= this.opLevel)
            return true;
        return sender.hasPermission(extensionPermission + ".*") || sender.hasPermission(extensionPermission + "." + permission);
    }

    public boolean hasPermission(Player player) {
        if(player.hasPermission("*") || player.getPermissionLevel() >= opLevel) return true;
        return player.hasPermission(extensionPermission);
    }

    public boolean hasPermission(Player player, String permission, int opLevel) {
        if (player.hasPermission("*")|| player.getPermissionLevel() >= opLevel)
            return true;
        return player.hasPermission(extensionPermission + ".*") || player.hasPermission(extensionPermission + "." + permission);
    }

    public boolean hasPermission(Player player, int opLevel) {
        if(player.hasPermission("*") || player.getPermissionLevel() >= opLevel) return true;
        return player.hasPermission(extensionPermission);
    }
}
