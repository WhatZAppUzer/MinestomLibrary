package dev.whatsappuser.minestom.lib.area;

import dev.whatsappuser.minestom.lib.location.Vector2;
import dev.whatsappuser.minestom.lib.location.Point;

import java.awt.*;
import java.util.List;

/**
 * development by TimoH created on 11:56:57 | 30.12.2022
 */

public interface Area extends Iterable<Point> {

    boolean contains(Point paramPoint);

    Contour contour();

    double minY();

    double maxY();

    default java.awt.geom.Area geometry() {
        List<Vector2> vertices = contour().vertices();
        int[] xpoints = new int[vertices.size()];
        int[] ypoints = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            xpoints[i] = (int) vertices.get(i).x();
            ypoints[i] = (int) vertices.get(i).y();
        }
        return new java.awt.geom.Area(new Polygon(xpoints, ypoints, vertices.size()));
    }

    default boolean intersects(Area other) {
        if(minY() < other.maxY() && maxY() > other.minY()) {
            java.awt.geom.Area tmp = geometry();
            tmp.intersect(other.geometry());
            return !tmp.isEmpty();
        }
        return false;
    }
}
