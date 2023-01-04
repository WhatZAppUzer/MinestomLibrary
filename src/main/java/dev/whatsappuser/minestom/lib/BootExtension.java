package dev.whatsappuser.minestom.lib;

import dev.whatsappuser.minestom.lib.permissions.PermissionProvider;
import lombok.Getter;

/**
 * development by TimoH created on 16:04:12 | 04.01.2023
 */
@Getter
public abstract class BootExtension extends Extension {

    private static PermissionProvider provider;

    @Override
    public void preInitialize() {
        provider = new PermissionProvider(getOrigin().getName());
    }
}
