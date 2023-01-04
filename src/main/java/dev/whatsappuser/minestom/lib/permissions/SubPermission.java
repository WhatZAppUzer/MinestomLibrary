package dev.whatsappuser.minestom.lib.permissions;

/**
 * development by TimoH created on 15:54:46 | 04.01.2023
 */

public class SubPermission extends PermissionProvider {

    private final PermissionProvider handle;

    public SubPermission(PermissionProvider handle, String extensionPermission) {
        super(handle.getExtensionPermission() + "." + extensionPermission);
        this.handle = handle;
    }

    public SubPermission(PermissionProvider handle, String extensionPermission, int opLevel) {
        super(opLevel, handle.getExtensionPermission() + "." + extensionPermission);
        this.handle = handle;
    }
}
