package ru.academits.bondyuk.hardrange;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return (number >= from) && (to >= number);
    }

    public static boolean isNotIntersection(Range range1, Range range2) {
        if (range1.getTo() < range2.getFrom()) {
            return true;
        }

        if (range2.getTo() < range1.getFrom()) {
            return true;
        }

        return false;
    }

    public static Range getIntersectionRange(Range range1, Range range2) {
        if (isNotIntersection(range1, range2)) {
            return null;
        }

        double intersectionRangeFrom = Math.max(range1.getFrom(), range2.getFrom());
        double intersectionRangeTo = Math.min(range1.getTo(), range2.getTo());

        return new Range(intersectionRangeFrom, intersectionRangeTo);
    }

    public static Range[] getUnionRange(Range range1, Range range2) {
        if (isNotIntersection(range1, range2)) {
            Range[] ranges = new Range[2];
            ranges[0] = range1;
            ranges[1] = range2;

            return ranges;
        }

        double unionRangeFrom = Math.min(range1.getFrom(), range2.getFrom());
        double unionRangeTo = Math.max(range1.getTo(), range2.getTo());

        Range[] ranges = new Range[2];
        ranges[0] = new Range(unionRangeFrom, unionRangeTo);

        return ranges;
    }

    public static Range[] getDifferenceRange(Range range1, Range range2) {
        if (isNotIntersection(range1, range2)) {
            Range[] ranges = new Range[2];
            ranges[0] = range1;

            return ranges;
        }

        if (range1.getFrom() < range2.getFrom() && range1.getTo() < range2.getTo()) {
            Range[] ranges = new Range[2];
            ranges[0] = new Range(range1.getFrom(), range2.getFrom());

            return ranges;
        }

        if (range2.getFrom() < range1.getFrom() && range2.getFrom() < range1.getTo()) {
            Range[] ranges = new Range[2];
            ranges[0] = new Range(range2.getTo(), range1.getTo());

            return ranges;
        }

        if (range1.getFrom() < range2.getFrom() && range2.getTo() < range1.getTo()) {
            Range[] ranges = new Range[2];
            ranges[0] = new Range(range1.getFrom(), range2.getFrom());
            ranges[1] = new Range(range2.getTo(), range1.getTo());

            return ranges;
        }

        return null;
    }
}
