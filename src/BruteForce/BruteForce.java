/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BruteForce;

import GUI.Edge;
import GUI.Point;
import GUI.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcvsa
 */
public class BruteForce {

    /**
     * default constructor
     *
     * empty as we only need the methods
     */
    public BruteForce() {
    }

    /**
     * updates the polygons internalEdge and internalEdgeSum variables
     *
     * @param p polygon that is to be updated
     */
    public void updatePolgyon(Polygon p) {
        List<Edge> curr = getEfficientEdges(p.getVertices());
        p.setInternalEdges(curr);
        p.setInternalEdgeSum(edgeSum(curr));
        System.out.println("BRUTE FORCE");
    }

    /**
     * Finds the tessellations with the smallest sum
     * 
     * @param vertices
     * @return 
     */
    public List<Edge> getEfficientEdges(List<Point> vertices) {

        /**
         * placeholder for storing the answer
         */
        List<Edge> ans = new ArrayList<>();
        
        /**
         * initialized as the the maximum value of double to easily 
         * overwrite it when comparing for the first time 
         */
        double ansEdgeSum = Double.MAX_VALUE;

        /**
         * gather all possible tessellation combinations
         */
        List<List<Edge>> temp = getAllTessellations(vertices);
        
        /**
         * iterate through the list and compare each value
         */
        for (List<Edge> curr : temp) {

            double currSum = this.edgeSum(curr);
            
            /**
             * if the current sum is smaller than the saved answer
             * then overwrite the ans and ansEdgeSum with the new best answer
             */
            if (ansEdgeSum > currSum) {
                ansEdgeSum = currSum;
                ans = curr;
            }
        }

        return ans;
    }

    public List<List<Edge>> getAllTessellations(List<Point> vertices) {
        return dfs(vertices, 0, vertices.size() - 1);
    }

    /**
     * returns a list of all possible combinations of internal edges based on the convex
     * polygon points passed through
     *
     * @param vertices convex polygon vertices
     * @param start first vertices of the polygon
     * @param end last vertices of the polygon
     * @return list of edge objects
     */
    private List<List<Edge>> dfs(List<Point> vertices, int start, int end) {

        /**
         * used for the answer returned
         */
        List<List<Edge>> allTessellations = new ArrayList<>();

        /**
         * Base Case: Return an empty list if current polygon is already a
         * triangle. If the difference between the end vertices and the start
         * vertices is less than 3, there can't be any possible tessellations
         * made
         */
        if (end - start < 3) {
            return allTessellations;
        }

        /**
         * iterate through the current polygon
         */
        for (int k = start + 1; k < end; k++) {

            /**
             * placeholder for storing possible tessellations
             */
            List<Edge> temp = new ArrayList<>();

            /**
             * Add the length of the internal edge to the list 
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
             * get the left and right tessellations
             */
            List<List<Edge>> left = dfs(vertices, start, k);
            List<List<Edge>> right = dfs(vertices, k, end);

            /**
             * if the left and right tessellations are empty then only add the
             * temp list
             * 
             * if not, then get the cartesian product of each side to get every 
             * possible tessellation with both lists
             */
            if (left.isEmpty() && right.isEmpty()) 
                allTessellations.add(temp);
            else
                allTessellations.addAll(combine(left, right, temp));

        }

        return allTessellations;

    }

    /**
     * helper function for getting the cartesian product of the left and right 
     * tessellations
     * 
     * @param left          left side tessellation
     * @param right         right side tessellations    
     * @param middle        the list of edges from the starting polygon
     * @return              a list of all the possible tessellations
     */
    private List<List<Edge>> combine(List<List<Edge>> left, List<List<Edge>> right, List<Edge> middle) {
        List<List<Edge>> combination = new ArrayList<>();

        /** 
         * return the right list if left is empty
         */
        if (left.isEmpty() && !right.isEmpty()) {
            combination = right;

        /**
         * return the left list if right is empty 
         */
        } else if (!left.isEmpty() && right.isEmpty()) {
            combination = left;

        /**
         * combines both left and right lists
         */
        } else {
            for (int i = 0; i < left.size(); i++) {
                for (int j = 0; j < right.size(); j++) {

                    List<Edge> temp = new ArrayList<>();
                    temp.addAll(left.get(i));
                    temp.addAll(right.get(j));
                    combination.add(temp);

                }
            }
        }

        /**
         * adds the internal edges to every possible tessellation gathered from 
         * the left and right tessellation
         */
        combination.forEach((curr) -> {
            curr.addAll(middle);
        });

        return combination;
    }

    /**
     * Calculates the sum of all the edge object lengths
     *
     * @param e A list containing edge objects
     * @return sum
     */
    public double edgeSum(List<Edge> e) {
        double sum = 0;

        sum = e.stream()
                .map((curr)
                        -> curr.length()).reduce(sum, (accumulator, _item) -> accumulator + _item);

        return sum;
    }

    /**
     * For Testing
     *
     * @param args
     */
    public static void main(String[] args) {
        BruteForce bf = new BruteForce();
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
        Polygon p = new Polygon(7, 100, 100);
//        
//        
//        int pCount = 0; 
//        for (Point point : p.getVertices()) {
//            System.out.println(pCount + ": " + point);
//            pCount++;
//        }
//        System.out.println();

        List<List<Edge>> e = bf.getAllTessellations(p.getVertices());
//        List<Edge> e = bf.getEfficientEdges(p.getVertices()); 
        int count = 1;
        for (List<Edge> curr : e) {
            System.out.println(count + ":  " + curr);
            count++;
        }
    }
}
