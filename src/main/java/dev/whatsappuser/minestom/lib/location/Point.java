package dev.whatsappuser.minestom.lib.location;

/**
 * development by TimoH created on 12:05:50 | 30.12.2022
 */

public sealed interface Point permits Vector, Position {

    double x();

    double y();

    double z();

    default int blockX() {
        return (int) x();
    }

    default int blockY() {
        return (int) y();
    }

    default int blockZ() {
        return (int) z();
    }

    Point withX(double x);

    Point withY(double y);

    Point withZ(double z);

    default double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    default double distanceSquared(Point other) {
        double diffX = x() - other.x();
        double diffY = y() - other.y();
        double diffZ = z() - other.z();
        return diffX * diffX + diffY * diffY + diffZ * diffZ;
    }

    default Point addX(double x) {
        return withX(x() + x);
    }

    default Point addY(double y) {
        return withY(y() + y);
    }

    default Point addZ(double z) {
        return withZ(z() + z);
    }

    Point scale(double n);

    Point divide(double n);

    Point add(double x, double y, double z);

    Point add(Point other);

    Point add(double n);

    Point floor();

    Point ceil();

    default Point subtract(Point other) {
        return add(-other.x(), -other.y(), -other.z());
    }

    default Point subtract(double x, double y, double z) {
        return add(-x, -y, -z);
    }

    default Point subtract(double n) {
        return add(-n);
    }

}
