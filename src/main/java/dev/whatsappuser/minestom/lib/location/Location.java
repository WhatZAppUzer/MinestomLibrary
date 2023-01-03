package dev.whatsappuser.minestom.lib.location;

import dev.whatsappuser.minestom.lib.world.World;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.block.Block;

import java.util.Objects;

/**
 * development by TimoH created on 12:06:25 | 30.12.2022
 */

public final class Location implements Cloneable, Position {

    private double x, y, z;
    private float yaw, pitch;
    private World world;

    public static final Location ZERO = new Location(null, 0, 0, 0, 0,0);

    public Location(World world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Location(World world, Point point) {
        this(world, point.x(), point.y(), point.z(), 0, 0);
    }

    public Location(Position position) {
        this(null, position.x(), position.y(), position.z(), position.yaw(), position.pitch());
    }

    public Location(Point point) {
        this(null, point.x(), point.y(), point.z(), 0, 0);
    }

    public Location withPosition(Position position) {
        return new Location(world, position.x(), position.y(), position.z(), position.yaw(), position.pitch());
    }

    public Location withWorldName(World world) {
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public Location withX(double x) {
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public Location withY(double y) {
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public Location withZ(double z) {
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public Location addX(double x) {
        return withX(x() + x);
    }

    @Override
    public Location addY(double y) {
        return withX(y() + y);
    }

    @Override
    public Location addZ(double z) {
        return withX(z() + z);
    }

    @Override
    public Location scale(double n) {
        return new Location(world, x() * n, y() * n, z() * n, yaw, pitch);
    }

    @Override
    public Location divide(double n) {
        return new Location(world, x / n, y / n, z / n, yaw, pitch);
    }

    @Override
    public Location add(double x, double y, double z) {
        return new Location(world, x() + x, y() + y, z() + z, yaw, pitch);
    }

    @Override
    public Location add(Point other) {
        return new Location(world, x() + other.x(), y() + other.y(), z() + other.z(), yaw, pitch);
    }

    @Override
    public Location add(double n) {
        return new Location(world, x() + n, y() + n, z() + n, yaw, pitch);
    }

    @Override
    public Location floor() {
        return new Location(world, Math.floor(x()), Math.floor(y()), Math.floor(z()), yaw, pitch);
    }

    @Override
    public Location ceil() {
        return new Location(world, Math.ceil(x()), Math.ceil(y()), Math.ceil(z()), yaw, pitch);
    }

    @Override
    public float yaw() {
        return this.yaw;
    }

    @Override
    public float pitch() {
        return this.pitch;
    }

    @Override
    public Position withYaw(float yaw) {
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public Position withPitch(float pitch) {
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public Position withYawPitch(float yaw, float pitch) {
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public Position withPoint(Point point) {
        return new Location(world, point.x(), point.y(), point.z(), yaw, pitch);
    }

    @Override
    public Location subtract(Point other) {
        return add(new Vector(-other.x(), -other.y(), -other.z()));
    }

    @Override
    public Location subtract(double x, double y, double z) {
        return add(-x, -y, -z);
    }

    public Chunk getChunk() {
        return world.getChunkAt(this);
    }

    public Block getBlock() {
        return world.getBlockAt(this);
    }

    public World world() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public Location subtract(double n) {
        return add(-n);
    }

    public Vector getDirection() {
        Vector vector = new Vector(x, y, z);

        double rotX = this.yaw();
        double rotY = this.pitch();

        vector.addY(-Math.sin(Math.toRadians(rotY)));

        double xz = Math.cos(Math.toRadians(rotY));

        vector.addX(-xz * Math.sin(Math.toRadians(rotX)));
        vector.addZ(xz * Math.cos(Math.toRadians(rotX)));

        return vector;
    }

    public double distance(Location o) {
        return Math.sqrt(distanceSquared(o));
    }

    public Location multiply(double m) {
        x *= m;
        y *= m;
        z *= m;
        return this;
    }

    public Location zero() {
        x = 0;
        y = 0;
        z = 0;
        return this;
    }

    public Vector toVector() {
        return new Vector(x, y, z);
    }

    @Override
    public Location clone() {
        try {
            return (Location) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if  (!(obj instanceof Location other)) {
            return false;
        }
        if ( other == this ) {
            return true;
        }
        double epsilon = 0.0001d;
        return Objects.equals(world, other.world)
                && Math.abs(x - other.x) < epsilon
                && Math.abs(y - other.y) < epsilon
                && Math.abs(z - other.z) < epsilon
                && Math.abs(yaw - other.yaw) < epsilon
                && Math.abs(pitch - other.pitch) < epsilon;
    }

    @Override
    public String toString() {
        return "Location{world=" + world + ", x=" + x + ", y=" + y + ", z=" + z + ", yaw=" + yaw + ", pitch=" + pitch + "}";
    }
}
