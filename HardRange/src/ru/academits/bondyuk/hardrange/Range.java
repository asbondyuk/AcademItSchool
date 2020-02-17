package ru.academits.bondyuk.hardrange;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public Range(Range range) {
        this.from = range.getFrom();
        this.to = range.getTo();
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

    public boolean isIntersection(Range range) {
        if (this.getTo() < range.getFrom()) {
            return false;
        }

        if (range.getTo() < this.getFrom()) {
            return false;
        }

        return true;
    }

    public Range getIntersection(Range range) {
        if (!isIntersection(range)) {
            return null;
        }

        double intersectionRangeFrom = Math.max(this.getFrom(), range.getFrom());
        double intersectionRangeTo = Math.min(this.getTo(), range.getTo());

        return new Range(intersectionRangeFrom, intersectionRangeTo);
    }

    public Range[] getUnion(Range range) {
        if (!isIntersection(range)) {
            Range[] ranges = new Range[2];
            ranges[0] = new Range(this);
            ranges[1] = new Range(range);

            return ranges;
        }

        double unionRangeFrom = Math.min(this.getFrom(), range.getFrom());
        double unionRangeTo = Math.max(this.getTo(), range.getTo());

        Range[] ranges = new Range[2];
        ranges[0] = new Range(unionRangeFrom, unionRangeTo);

        return ranges;
    }

    public Range[] getDifference(Range range) {
        if (!isIntersection(range)) {
            Range[] ranges = new Range[2];
            ranges[0] = new Range(this);

            return ranges;
        }

        if (this.getFrom() < range.getFrom() && this.getTo() < range.getTo()) {
            Range[] ranges = new Range[2];
            ranges[0] = new Range(this.getFrom(), range.getFrom());

            return ranges;
        }

        if (range.getFrom() < this.getFrom() && range.getFrom() < this.getTo()) {
            Range[] ranges = new Range[2];
            ranges[0] = new Range(range.getTo(), this.getTo());

            return ranges;
        }

        if (this.getFrom() < range.getFrom() && range.getTo() < this.getTo()) {
            Range[] ranges = new Range[2];
            ranges[0] = new Range(this.getFrom(), range.getFrom());
            ranges[1] = new Range(range.getTo(), this.getTo());

            return ranges;
        }

        return null;
    }
}