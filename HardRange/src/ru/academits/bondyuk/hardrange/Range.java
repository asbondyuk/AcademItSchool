package ru.academits.bondyuk.hardrange;

// TODO из письма пункты: 3, 4, 6,
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

    private boolean isIntersection(Range range) {
        return (!(this.to < range.getFrom())) && (!(range.getTo() < this.from));
    }

    public Range getIntersection(Range range) {
        if (!isIntersection(range)) {
            return null;
        }

        double intersectionRangeFrom = Math.max(this.from, range.getFrom());
        double intersectionRangeTo = Math.min(this.to, range.getTo());

        return new Range(intersectionRangeFrom, intersectionRangeTo);
    }

    public Range[] getUnion(Range range) {
        if (!isIntersection(range)) {
            Range[] ranges = new Range[2];
            ranges[0] = new Range(this);
            ranges[1] = new Range(range);

            return ranges;
        }

        double unionRangeFrom = Math.min(this.from, range.getFrom());
        double unionRangeTo = Math.max(this.to, range.getTo());

        return new Range[]{new Range(unionRangeFrom, unionRangeTo)};
    }

    public Range[] getDifference(Range range) {
        if (!isIntersection(range)) {
            return new Range[]{new Range(this)};
        }

        if (this.from < range.getFrom() && this.to < range.getTo()) {
            return new Range[]{new Range(this.from, range.getFrom())};
        }

        if (range.getFrom() < this.from && range.getFrom() < this.to) {
            return new Range[]{new Range(range.getTo(), this.to)};
        }

        if (this.from < range.getFrom() && range.getTo() < this.to) {
            Range[] ranges = new Range[2];
            ranges[0] = new Range(this.from, range.getFrom());
            ranges[1] = new Range(range.getTo(), this.to);

            return ranges;
        }

        return null;
    }
}