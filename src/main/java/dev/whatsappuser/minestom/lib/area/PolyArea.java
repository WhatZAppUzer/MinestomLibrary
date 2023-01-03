package dev.whatsappuser.minestom.lib.area;

import bentleyottmann.BentleyOttmann;
import bentleyottmann.ISegment;
import dev.whatsappuser.minestom.lib.location.Point;
import dev.whatsappuser.minestom.lib.location.Segment2;
import dev.whatsappuser.minestom.lib.location.Vector2;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * development by TimoH created on 12:47:43 | 30.12.2022
 */

public record PolyArea(double minY, double maxY, List<Vector2> points) implements Area {

    public PolyArea(double minY, double maxY, List<Vector2> points) {
        points = new ArrayList<>(points);

        // remove points that are on a straight line between its two surrounding points, also removes duplicates
        int index = 2;
        while ( index <= points.size() ) {
            Vector2 current = index == points.size() ? points.get(0) : points.get(index);
            Vector2 oneToTwo = points.get(index - 1).subtract(points.get(index - 2)).normalize();
            Vector2 twoToThree = current.subtract(points.get(index - 1)).normalize();
            if (oneToTwo.equals(twoToThree)) {
                points.remove(index - 1);
                continue;
            }
            index++;
        }

        if (points.size() < 3) {
            throw new IllegalArgumentException("Polygon area requires at least 3 points.");
        }

        this.minY = minY;
        this.maxY = maxY;
        this.points = List.copyOf(points);
    }

    public PolyArea(double minY, double maxY, Vector2... points) {
        this(minY, maxY, Arrays.asList(points));
    }

    public int size() {
        return points.size();
    }

    public boolean isComplex() {
        List<ISegment> segments = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            segments.add(new Segment2(points.get(i), points.get(i + 1)));
        }
        segments.add(new Segment2(points.get(points.size() - 1), points.get(0)));

        BentleyOttmann bentleyOttmann = new BentleyOttmann(Vector2::new);
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.findIntersections();

        return bentleyOttmann.intersections().size() > 0;
    }

    public boolean isConvex() {
        if (points.size() < 4) {
            return true;
        }

        if (isComplex()) {
            return false;
        }

        boolean sign = false;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            double dx1 = points.get((i + 2) % n).x() - points.get((i + 1) % n).x();
            double dy1 = points.get((i + 2) % n).y() - points.get((i + 1) % n).y();
            double dx2 = points.get(i).x() - points.get((i + 1) % n).x();
            double dy2 = points.get(i).y() - points.get((i + 1) % n).y();
            double zcrossproduct = dx1 * dy2 - dy1 * dx2;

            if (i == 0) {
                sign = zcrossproduct > 0;
            } else if (sign != (zcrossproduct > 0)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean contains(Point vector) {
        if (points.size() < 3) {
            return false;
        }

        int targetX = (int) Math.floor(vector.x()); //wide
        int targetY = (int) Math.floor(vector.y()); //height
        int targetZ = (int) Math.floor(vector.z()); //depth

        if (targetY < minY || targetY > maxY) {
            return false;
        }

        boolean inside = false;
        int npoints = points.size();
        double xNew, zNew;
        double x1, z1, x2, z2;
        long crossproduct;

        double xOld = points.get(npoints - 1).x();
        double zOld = points.get(npoints - 1).y();

        for (Vector2 point : points) {
            xNew = point.x();
            zNew = point.y();

            //Check for corner
            if (xNew == targetX && zNew == targetZ) {
                return true;
            }

            if (xNew > xOld) {
                x1 = xOld;
                x2 = xNew;
                z1 = zOld;
                z2 = zNew;
            } else {
                x1 = xNew;
                x2 = xOld;
                z1 = zNew;
                z2 = zOld;
            }
            if (x1 <= targetX && targetX <= x2) {
                crossproduct = ((long) targetZ - (long) z1) * (long) (x2 - x1)
                        - ((long) z2 - (long) z1) * (long) (targetX - x1);
                if (crossproduct == 0) {
                    if ((z1 <= targetZ) == (targetZ <= z2)) {
                        return true; //on edge
                    }
                } else if (crossproduct < 0 && (x1 != targetX)) {
                    inside = !inside;
                }
            }
            xOld = xNew;
            zOld = zNew;
        }

        return inside;
    }

    @Override
    public Contour contour() {
        return new Contour(points);
    }

    @NotNull
    @Override
    public Iterator<Point> iterator() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}

