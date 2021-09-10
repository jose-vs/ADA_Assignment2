package GreedyMethod;

import GUI.Edge;
import GUI.Point;
import GUI.Polygon;

import javax.sound.sampled.Line;
import java.awt.geom.Line2D;
import java.lang.reflect.Array;
import java.util.*;

/**
 *
 * @author libby
 */
public class GreedyMethod {
    private ArrayList<Edge> edges;
    private List<Edge> polygonEdges;

    public GreedyMethod(Polygon p) {
        List<Point> pointsConvert = new ArrayList<>();
        for (Point point : p.getVertices()) {
            pointsConvert.add(point);
        }
        edges = new ArrayList<>();
        setPolygonEdges(p);

        greedyNew(pointsConvert);
    }


    public void setPolygonEdges(Polygon p) {
        polygonEdges = new ArrayList<>();
        //set edges
        for (int j = 0; j < p.getNumOfVertices(); j++) {
            Edge edge1;
            if (j + 1 < p.getNumOfVertices()) {
                edge1 = new Edge(p.getVertices().get(j), p.getVertices().get(j + 1));
            }
            else {
                edge1 = new Edge(p.getVertices().get(j), p.getVertices().get(0));
            }
            polygonEdges.add(edge1);
        }
    }


    public void greedyNew(List<Point> pointsGiven) {
        ArrayList indexesTickedOff = new ArrayList<>();
        List<Distance>[] distances = new ArrayList[pointsGiven.size()];
        ArrayList<Line2D> lines = new ArrayList<>();

        //initialise distances
        for (int b = 0; b < distances.length; b++) {
            distances[b] = new ArrayList<>();
        }

        for (int j = 0; j < pointsGiven.size(); j++) {
            double min = Double.MAX_VALUE;
            int minIndex = 0;
            boolean checkPoint = true;


            //check if the point is already definitely part of a triangle
            if (indexesTickedOff.contains(j)) {
                checkPoint = false;
            }

            //only check the point if it isn't definitely part of a triangle
            if (checkPoint == true) {
                int start = j + 2;
                int index = start;
                //check distance between the current point being checked and other points
                for (int i = start; i < pointsGiven.size() + start; i++) {
                    if (index >= pointsGiven.size() - 1) {
                        index = 0;
                    }
                    if (j != index) {
                        if (notContain(pointsGiven.get(j), index, distances, pointsGiven)) {
                            double distance = Math.sqrt(Math.pow((pointsGiven.get(j).getxPos() - pointsGiven.get(j).getyPos()), 2) + Math.pow((pointsGiven.get(index).getxPos() - pointsGiven.get(index).getyPos()), 2));
                            //add to both point j -> index and index -> j so it won't have to be calculated again
                            distances[j].add(new Distance(index, distance));
                            distances[index].add(new Distance(j, distance));
                        }
                    }
                    index++;
                }

                //find the minimum distance between the current point being checked and other points
                Collections.sort(distances[j], new Comparator<Distance>() {
                    @Override
                    public int compare(Distance o1, Distance o2) {
                        return o1.getDistance().compareTo(o2.getDistance());
                    }
                });

                for (Distance dist: distances[j]) {
                    if (dist.getDistance() < min) {
                        min = dist.getDistance();
                        minIndex = dist.getIndexB();
                    }
                }

                //add the minimum edge to the list of edges
                Edge addEdge = new Edge(pointsGiven.get(j), pointsGiven.get(minIndex));
                if (!polygonEdges.contains(addEdge)) {
                    edges.add(addEdge);
                    lines.add(new Line2D.Double(pointsGiven.get(j).getxPos(), pointsGiven.get(j).getyPos(), pointsGiven.get(minIndex).getxPos(), pointsGiven.get(minIndex).getyPos()));
                }

                //add a point to indexesTickedOff if its definitely part of a triangle
                if (j + 2 == minIndex || (minIndex == 0 && j == pointsGiven.size() - 2)) {
                    indexesTickedOff.add(j + 1);
                }
                else if (j - 2 == minIndex) {
                    indexesTickedOff.add(j - 1);
                }
                else if (j == 0 && minIndex == pointsGiven.size() - 2) {
                    indexesTickedOff.add(minIndex + 1);
                }

            }
        }
    }

    public boolean notContain(Point j, int index, List<Distance>[] distances, List<Point> pointsGiven) {
        for (Distance d: distances[index]) {
            if (pointsGiven.get(d.getIndexB()).getxPos() == j.getxPos() && pointsGiven.get(d.getIndexB()).getyPos() == j.getyPos()) {
                return false;
            }
        }
        return true;
    }

    public List<Edge> updateGreedy(Polygon p) {
        List<Edge> polygonEdges = new ArrayList<>();
        for (Edge e : edges) {
            polygonEdges.add(e);
        }
        p.setInternalEdges(polygonEdges);
        p.setInternalEdgeSum(edgeSum(polygonEdges));
        return polygonEdges;
    }


    /**
     *
     * @author jcvsa
     */
    public double edgeSum(List<Edge> e) {
        double sum = 0;

        sum = e.stream()
                .map((curr)
                        -> curr.length()).reduce(sum, (accumulator, _item) -> accumulator + _item);

        return sum;
    }

}

