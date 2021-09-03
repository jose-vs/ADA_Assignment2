package BruteForce;

import GUI.Point;
import GUI.PointLine;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author libby
 */
public class BruteForce {
    private Point[] interiorPoints;

    public BruteForce(Point[] points) {
        this.interiorPoints = points;
    }

    public void bruteForce() {
        //assume points are in order that they connect (assume edges) - go up by 2
        //find all distances
        //find all possible combinations
        //pick smallest combination

        HashMap<PointLine, Double> distances = new HashMap();


        //get all distances/all intersects possible
        for (int i = 0; i < interiorPoints.length; i++) {
            for (int j = 1; j < interiorPoints.length; j = j+2) {
                if (!interiorPoints[i].equals(interiorPoints[j])) {

                    Point pointA = new Point(interiorPoints[i].getxPos(), interiorPoints[i].getyPos());
                    Point pointB = new Point(interiorPoints[j].getxPos(), interiorPoints[j].getyPos());

                    if (!containsOpposite(pointA, pointB, distances)) {
                        PointLine points = new PointLine(pointA, pointB);

                        double distance = Math.sqrt(Math.pow((interiorPoints[i].getxPos() - interiorPoints[i].getyPos()), 2) + Math.pow((interiorPoints[j].getxPos() - interiorPoints[j].getyPos()), 2));

                        distances.put(points, distance);
                    }
                }
            }
        }

        //loop - add all distances together for each distance unless they intersect
        HashMap<Integer, ArrayList<PointLine>> possibleCombinations = new HashMap<>();
        HashMap<Integer, Double> possibleDistances = new HashMap<>();
        int ID = 0;
        for (PointLine line : distances.keySet()) {
            ArrayList<PointLine> combination = new ArrayList<>();
            Double distance = distances.get(line);
            combination.add(line);
            possibleCombinations.put(ID, combination);
            possibleDistances.put(ID, distance);
            for (PointLine inner : distances.keySet()) {
                if (!line.equals(inner)) {
                    Line2D line1 = new Line2D.Float(line.getPointA().getxPos(), line.getPointA().getyPos(), line.getPointB().getxPos(), line.getPointB().getyPos());
                    Line2D line2 = new Line2D.Float(inner.getPointA().getxPos(), inner.getPointA().getyPos(), inner.getPointB().getxPos(), inner.getPointB().getyPos());
                    if (!line1.intersectsLine(line2)) {
                        distance += distances.get(inner);
                        combination.add(inner);
                        possibleCombinations.put(ID, combination);
                        possibleDistances.put(ID, distance);
                    }
                }
            }
            ID++;
        }

        //get minimum
        Double minimum = Double.MAX_VALUE;
        Integer minimumID = 0;
        for (Integer i : possibleDistances.keySet()) {
            if (possibleDistances.get(i) < minimum) {
                minimum = possibleDistances.get(i);
                minimumID = i;
            }
        }

        //print out
        System.out.println("smallest distance is: " + minimum);
        for (int j = 0; j < possibleCombinations.get(minimumID).size(); j++) {
            System.out.println(possibleCombinations.get(minimumID).get(j).getPointA() + " to point " +
                    possibleCombinations.get(minimumID).get(j).getPointB());
            System.out.println("\n");
        }

    }

    public static boolean containsOpposite(Point pointA, Point pointB, HashMap<PointLine, Double> map) {
        for (PointLine key : map.keySet()) {
            if ((key.getPointA().getxPos() == pointB.getxPos()) && (key.getPointA().getyPos() == pointB.getyPos())) {
                if ((key.getPointB().getxPos() == pointA.getxPos()) && (key.getPointB().getyPos() == pointA.getyPos())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Point[] points = new Point[4];
        points[0] = new Point(1, 1);

        points[1] = new Point(4, 1);

        points[2] = new Point(1, 5);

        points[3]= new Point(4, 5);

        BruteForce shape = new BruteForce(points);
        shape.bruteForce();
    }
}
