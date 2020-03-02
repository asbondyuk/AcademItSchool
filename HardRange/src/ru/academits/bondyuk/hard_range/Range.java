package ru.academits.bondyuk.hard_range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public Range(Range range) {
        from = range.getFrom();
        to = range.getTo();
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

    private boolean isIntersection(Range range) {
        return (from < range.getFrom() && range.getTo() < to) || (range.getFrom() < from && to < range.getTo());
    }


    public Range[] getIntersection(Range range) {
        if (!isIntersection(range)) {
            return new Range[]{};
        }

        double intersectionRangeFrom = Math.max(from, range.getFrom());
        double intersectionRangeTo = Math.min(to, range.getTo());

        return new Range[]{new Range(intersectionRangeFrom, intersectionRangeTo)};
    }

    public Range[] getUnion(Range range) {
        if (!isIntersection(range)) {
            Range[] ranges = new Range[2];
            ranges[0] = new Range(this);
            ranges[1] = new Range(range);

            return ranges;
        }

        double unionRangeFrom = Math.min(from, range.getFrom());
        double unionRangeTo = Math.max(to, range.getTo());

        return new Range[]{new Range(unionRangeFrom, unionRangeTo)};
    }

    public Range[] getDifference(Range range) {
        if (!(range.getFrom() < from && to < range.getTo()) || !(from < range.getFrom() && range.getTo() < to)) {
            return new Range[]{new Range(this)};
        }

        if (range.getFrom() < from && to < range.getTo()) {
            return new Range[]{};
        }

        if (from < range.getFrom() && range.getTo() < to) {
            Range[] ranges = new Range[2];
            ranges[0] = new Range(from, range.getFrom());
            ranges[1] = new Range(range.getTo(), to);

            return ranges;
        }

        return null;
    }

    @Override
    public String toString() {
        return "(" + from +
                "; " + to +
                ')';
    }
}