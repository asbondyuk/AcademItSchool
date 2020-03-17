package ru.academits.bondyuk.hard_range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public Range(Range range) {
        from = range.from;
        to = range.to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return (number >= from) && (to >= number);
    }

    private boolean isIntersection(Range range) {
        return (from < range.from && range.from < to) || (range.from < from && from < range.to);
    }

    public Range getIntersection(Range range) {
        if (!isIntersection(range)) {
            return null;
        }

        double intersectionRangeFrom = Math.max(from, range.from);
        double intersectionRangeTo = Math.min(to, range.to);

        return new Range(intersectionRangeFrom, intersectionRangeTo);
    }

    public Range[] getUnion(Range range) {
        if (!isIntersection(range)) {
            return new Range[]{new Range(this), new Range(range)};
        }

        return new Range[]{new Range(Math.min(from, range.getFrom()), Math.max(to, range.getTo()))};
    }

    public Range[] getDifference(Range range) {
        if (!isIntersection(range)) {
            return new Range[]{new Range(this)};
        }

        if (from < range.from && to < range.to) {
            return new Range[]{new Range(from, range.from)};
        }

        if (range.from < from && range.to < to) {
            return new Range[]{new Range(range.to, to)};
        }

        if (range.from < from && to < range.to) {
            return new Range[]{null};
        }

        if (from < range.from && range.to < to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        return null;
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }
}