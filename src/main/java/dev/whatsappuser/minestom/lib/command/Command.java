package dev.whatsappuser.minestom.lib.command;

import dev.whatsappuser.minestom.lib.permissions.PermissionProvider;
import dev.whatsappuser.minestom.lib.permissions.SubPermission;
import lombok.Getter;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * development by TimoH created on 16:05:30 | 04.01.2023
 */
@Getter
public class Command extends net.minestom.server.command.builder.Command {

    protected String permission;
    protected SubPermission provider;

    public Command(@NotNull PermissionProvider provider, @NotNull String name, @Nullable String... aliases) {
        super(name, aliases);
        this.provider = new SubPermission(provider, name);
        setPermission(name);
        setCondition(this::defaultCondition);
    }

    public void setPermission(String permission) {
        this.permission = permission;
        updatePermissions();
    }

    public void updatePermissions() {
        this.provider.update(this.permission);
    }

    protected boolean defaultCondition(CommandSender sender, String command) {
        return sender instanceof ConsoleSender || this.provider.hasPermission((Player) sender);
    }
}
