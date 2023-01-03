package dev.whatsappuser.minestom.lib;

import dev.whatsappuser.minestom.lib.location.Location;
import dev.whatsappuser.minestom.lib.location.Position;
import dev.whatsappuser.minestom.lib.location.Vector;
import dev.whatsappuser.minestom.lib.world.World;
import dev.whatsappuser.minestom.lib.world.WorldInfo;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Entity;

/**
 * development by TimoH created on 11:56:40 | 30.12.2022
 */

public class MinestomMaths {

    public static Vector toVector(Point other) {
        return new Vector(other.x(), other.y(), other.z());
    }

    public static Vec toVec(net.minestom.server.coordinate.Point other) {
        return new Vec(other.x(), other.y(), other.z());
    }

    public static Pos toPos(Position other) {
        return new Pos(other.x(), other.y(), other.z(), other.yaw(), other.pitch());
    }

    public static Position toPosition(Pos pos) {
        return toLocation(pos);
    }

    public static Location toLocation(Pos pos) {
        return new Location(null, pos.x(), pos.y(), pos.z(), pos.yaw(), pos.pitch());
    }

    public static Location toLocation(Entity entity) {
        Location loc = toLocation(entity.getPosition());
        if ( entity.getInstance() != null ) {
            loc = loc.withWorldName((World) entity.getInstance());
        }
        return loc;
    }
}
