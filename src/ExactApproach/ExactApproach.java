/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExactApproach;

import GUI.Edge;
import GUI.Point;
import GUI.Polygon;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author jcvsa
 */
public class ExactApproach {

    private Set<List<Edge>> memo = new HashSet<>();

    public ExactApproach() {
    }

    /**
     * updates the polygons internalEdge and internalEdgeSum variables
     *
     * @param p polygon that is to be updated
     */
    public void updatePolgyon(Polygon p) {
        memo.clear();
        List<Edge> curr = getEfficientEdges(p.getVertices());
        p.setInternalEdges(curr);
        p.setInternalEdgeSum(edgeSum(curr));
    }

    public List<Edge> getEfficientEdges(List<Point> vertices) {

        int n = vertices.size();

        /**
         * memo for storing lists that has already been visited before
         */
        List<?>[][] memo = new List<?>[n][n];

        return dfs(vertices, 0, n - 1, memo);
    }

    private List<Edge> dfs(List<Point> vertices, int start, int end, List<?>[][] memo) {

        /**
         * placeholder for checking if current edge sum
         */
        double sum = Double.MAX_VALUE;

        /**
         * used for the answer returned
         */
        List<Edge> edge = new ArrayList<>();

        /**
         * if the current memo has been visited before, return the value in the
         * memo
         * 
         * the memo is considered to be visited before when the the start 
         * and end vertices are recognized and that the value stored in 
         * that location is not null
         */
        if (memo[start][end] != null) {
            return (List<Edge>) memo[start][end];
        }

        /**
         * Base Case: Return an empty list if current polygon is already a
         * triangle. If the difference between the end vertices and the start
         * vertices is less than 3, there can't be any possible tessellations
         * made
         */
        if (end - start < 3) {
            return edge;
        }

        /**
         * iterate through the current polygon with the start and end point 
         */
        for (int k = start + 1; k < end; k++) {

            /**
             * placeholder for storing possible tessellations
             */
            List<Edge> temp = new ArrayList<>();

            /**
             * Add the length of the internal edge
             *
             * An internal edge can only be internal if the k vertices is either
             * not the adjacent vertices of the start or end point of the
             * current tessellation of the polygon.
             */
            if (start + 1 < k) {
                temp.add(new Edge(new Point(vertices.get(k).getxPos(), vertices.get(k).getyPos()),
                        new Point(vertices.get(start).getxPos(), vertices.get(start).getyPos())));
            }
            if (end - 1 > k) {
                temp.add(new Edge(new Point(vertices.get(k).getxPos(), vertices.get(k).getyPos()),
                        new Point(vertices.get(end).getxPos(), vertices.get(end).getyPos())));
            }

            /**
             * calculate left and right side of tessellated polygon
             */
            temp.addAll(dfs(vertices, start, k, memo));
            temp.addAll(dfs(vertices, k, end, memo));

            /**
             * Checks if the current edge sum is less than the previous sum if
             * is less then overwrite the current edge object
             */
            double edgeSum = edgeSum(temp);
            if (sum > edgeSum) {
                sum = edgeSum;
                edge = temp;
            }
        }

        memo[start][end] = edge;

        return (List<Edge>) memo[start][end];
    }

    /**
     * Calculates the sum of all the edge object lengths
     *
     * @param e A list containing edge objects
     * @return sum
     */
    public double edgeSum(List<Edge> e) {
        double sum = 0;

        sum
                = e.stream()
                        .map((curr)
                                -> curr.length()).reduce(sum, (accumulator, _item) -> accumulator + _item);

        return sum;
    }

    /**
     * For Testing
     */
    public static void main(String[] args) {
        ExactApproach ea = new ExactApproach();
        List<Point> testList = new ArrayList<>();
        testList.add(new Point(1, 2));
        testList.add(new Point(1, 4));
        testList.add(new Point(3, 5));
        testList.add(new Point(4, 3));
        testList.add(new Point(3, 1));

//        testList.add(new Point(1,1));
//        testList.add(new Point(0,4));
//        testList.add(new Point(3,5));
//        testList.add(new Point(4,4));
//        testList.add(new Point(4,2));
//        testList.add(new Point(2,0));
        Polygon p = new Polygon(20, 10, 10);

        List<Edge> e = ea.getEfficientEdges(p.getVertices());
        Iterator it = e.iterator();
        int count = 1;
        while (it.hasNext()) {
            Edge curr = (Edge) it.next();
            System.out.println(count + ": " + curr.toString());
            count++;
        }
    }
}
