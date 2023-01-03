package dev.whatsappuser.minestom.lib.location;

import bentleyottmann.IPoint;

import java.util.Collection;

/**
 * development by TimoH created on 11:59:12 | 30.12.2022
 */

public record Vector2(double x, double y) implements IPoint {

    public static final Vector2 ZERO = new Vector2(0, 0);

    public Vector2 addX(double x) {
        return withX(this.x + x);
    }

    public Vector2 addY(double y) {
        return withY(this.y + y);
    }

    public Vector2 withX(double x) {
        return new Vector2(x, y);
    }

    public Vector2 withY(double y) {
        return new Vector2(x, y);
    }

    public Vector2 scale(double v) {
        return new Vector2(x * v, y * v);
    }

    public Vector2 add(double x, double y) {
        return new Vector2(this.x + x, this.y + y);
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x(), this.y + other.y());
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x(), this.y - other.y());
    }

    public Vector2 add(Collection<Vector2> others) {
        double x = this.x;
        double y = this.y;
        for ( Vector2 vec : others ) {
            x += vec.x;
            y += vec.y;
        }
        return new Vector2(x, y);
    }

    public double distance(Vector2 other) {
        return Math.sqrt(distanceSquared(other));
    }

    public double distanceSquared(Vector2 other) {
        double diffX = x() - other.x();
        double diffY = y() - other.y();
        return diffX * diffX + diffY * diffY;
    }

    public double length() {
        return distance(ZERO);
    }

    public Vector2 normalize() {
        double length = length();
        return new Vector2(x / length, y / length);
    }

    @Override
    public boolean equals(Object obj) {
        if  (!(obj instanceof Vector2 other)) {
            return false;
        }
        if ( other == this ) {
            return true;
        }
        double epsilon = 0.0001d;
        return Math.abs(x - other.x) < epsilon
                && Math.abs(y - other.y) < epsilon;
    }

    @Override
    public String toString() {
        return "Vector2{x=" + x + ", y=" + y + "}";
    }
}
