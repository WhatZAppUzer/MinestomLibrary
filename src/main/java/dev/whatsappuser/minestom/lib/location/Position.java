package dev.whatsappuser.minestom.lib.location;

/**
 * development by TimoH created on 12:05:59 | 30.12.2022
 */

public sealed interface Position extends Point permits Location {

    float yaw();

    float pitch();

    Position withYaw(float yaw);

    Position withPitch(float pitch);

    Position withYawPitch(float yaw, float pitch);

    Position withPoint(Point point);
}
