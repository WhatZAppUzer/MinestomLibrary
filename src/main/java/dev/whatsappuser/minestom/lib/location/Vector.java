package dev.whatsappuser.minestom.lib.location;

/**
 * development by TimoH created on 12:05:24 | 30.12.2022
 */

public record Vector(double x, double y, double z) implements Point {

    public static final Vector ZERO = new Vector(0, 0, 0);

    public static Vector of(Point point) {
        return new Vector(point.x(), point.y(), point.z());
    }

    @Override
    public Vector addX(double x) {
        return withX(this.x + x);
    }

    @Override
    public Vector addY(double y) {
        return withY(this.y + y);
    }

    @Override
    public Vector addZ(double z) {
        return withZ(this.z + z);
    }

    @Override
    public Vector withX(double x) {
        return new Vector(x, y, z);
    }

    @Override
    public Vector withY(double y) {
        return new Vector(x, y, z);
    }

    @Override
    public Vector withZ(double z) {
        return new Vector(x, y, z);
    }

    @Override
    public Vector scale(double v) {
        return new Vector(x * v, y * v, z * v);
    }

    @Override
    public Vector divide(double n) {
        return new Vector(x / n, y / n, z / n);
    }

    @Override
    public Vector add(double x, double y, double z) {
        return new Vector(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public Vector add(Point other) {
        return new Vector(this.x + other.x(), this.y + other.y(), this.z + other.z());
    }

    @Override
    public Vector add(double n) {
        return new Vector(x + n, y + n, z + n);
    }

    @Override
    public Vector floor() {
        return new Vector(Math.floor(x), Math.floor(y), Math.floor(z));
    }

    @Override
    public Vector ceil() {
        return new Vector(Math.ceil(x), Math.ceil(y), Math.ceil(z));
    }

    @Override
    public Vector subtract(Point other) {
        return add(new Vector(-other.x(), -other.y(), -other.z()));
    }

    @Override
    public Vector subtract(double x, double y, double z) {
        return add(-x, -y, -z);
    }

    @Override
    public Vector subtract(double n) {
        return add(-n);
    }

    public double length() {
        return distance(ZERO);
    }

    public Vector normalize() {
        double length = length();
        return new Vector(x / length, y / length, z / length);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector other)) {
            return false;
        }
        if ( other == this ) {
            return true;
        }
        double epsilon = 0.0001d;
        return Math.abs(x - other.x) < epsilon
                && Math.abs(y - other.y) < epsilon
                && Math.abs(z - other.z) < epsilon;
    }

    @Override
    public String toString() {
        return "Vector{x=" + x + ", y=" + y + ", z=" + z + "}";
    }
}
