package GUI;

import java.awt.geom.Line2D;

public class Edge {
    private Point start;
    private Point end;

    public Edge(Point start, Point end){
        this.start = start;
        this.end = end;
    }

    public double length(){
        return
                Math.sqrt((
                        Math.pow((start.getxPos() - end.getxPos()), 2) +
                                Math.pow((start.getyPos() - end.getyPos()), 2)));
    }

    public boolean edgeIntersecting(Edge other) {

        Line2D p1 = new Line2D.Double
                (start.getxPos(), start.getxPos(), end.getxPos(), end.getyPos());
        Line2D p2 = new Line2D.Double
                (other.getStart().getxPos(), other.getStart().getxPos(),
                        other.getEnd().getxPos(), other.getEnd().getyPos());

        boolean isIntersecting = p1.intersectsLine(p2);

        return isIntersecting;
    }

    /**
     * @return the start
     */
    public Point getStart() {
        return start;
    }

    /**
     * @return the end
     */
    public Point getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        Edge e = (Edge) o;

        return (e.start == this.start && e.end == this.end) ||
                (e.start == this.end && e.end == this.start);

    }

    @Override
    public String toString() {
        return getStart().toString() + " -> " + getEnd().toString();
    }

}
