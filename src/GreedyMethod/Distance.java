package GreedyMethod;

/**
 *
 * @author libby
 */
public class Distance {
    private int indexB;
    private double distance;

    public Distance(int b, Double d) {
        indexB = b;
        this.distance = d;
    }

    public int getIndexB() {
        return indexB;
    }

    public Double getDistance() {
        return distance;
    }

}
