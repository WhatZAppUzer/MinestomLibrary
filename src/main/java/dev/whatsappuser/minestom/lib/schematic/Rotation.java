package dev.whatsappuser.minestom.lib.schematic;

import org.jetbrains.annotations.NotNull;

/**
 * development by TimoH created on 19:44:11 | 31.12.2022
 */

public enum Rotation {
    NONE,
    CLOCKWISE_90,
    CLOCKWISE_180,
    CLOCKWISE_270;

    public Rotation rotate(Rotation rotation) {
        return values()[(ordinal() + rotation.ordinal()) % 4];
    }

    /**
     * Converts a Minestom {@link net.minestom.server.utils.Rotation} to a rotation usable in a schematic.
     * <p>
     * Minestom rotation supports 45 degree angles, if passed to this function they will be rounded down to the nearest 90 degree angle.
     */
    public static @NotNull Rotation from(@NotNull net.minestom.server.utils.Rotation rotation) {
        return values()[rotation.ordinal() / 2];
    }
}
