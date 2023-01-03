package dev.whatsappuser.minestom.lib.location;

/**
 * development by TimoH created on 12:45:21 | 30.12.2022
 */

public enum CardinalDirection {
    NORTH(new Vector(0, 0, -1)),
    EAST(new Vector(1, 0, 0)),
    SOUTH(new Vector(0, 0, 1)),
    WEST(new Vector(-1, 0, 0));

    private final Vector direction;

    CardinalDirection(Vector direction) {
        this.direction = direction;
    }

    public CardinalDirection opposite() {
        CardinalDirection[] values = values();
        int ordinal = (this.ordinal() + 2) % values.length;
        return values[ordinal];
    }

    public CardinalDirection clockwise() {
        CardinalDirection[] values = values();
        int ordinal = (this.ordinal() + 1) % values.length;
        return values[ordinal];
    }

    public CardinalDirection counterclockwise() {
        CardinalDirection[] values = values();
        int ordinal = (this.ordinal() + 3) % values.length;
        return values[ordinal];
    }

    public Point forwards(Point point, double amount) {
        return point.add(direction.scale(amount));
    }

    public Point backwards(Point point, double amount) {
        return point.add(direction.scale(-amount));
    }

    public Vector direction() {
        return direction;
    }

    //

    public static CardinalDirection fromYaw(float yaw) {
        if (yaw >= 135 || yaw < -135) {
            return CardinalDirection.NORTH;
        }
        if (yaw >= -135 && yaw < -45) {
            return CardinalDirection.EAST;
        }
        if (yaw >= -45 && yaw < 45) {
            return CardinalDirection.SOUTH;
        }
        return CardinalDirection.WEST;
    }

}