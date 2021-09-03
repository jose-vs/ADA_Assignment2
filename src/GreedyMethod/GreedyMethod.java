/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GreedyMethod;

import GUI.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author libby
 */
public class GreedyMethod {
    static ArrayList<Point[]> triangles;
    static ArrayList<Point> pointsLeft;


    public static double greedy(List<Point> points, int start, int end) {
        Collections.copy(pointsLeft, points);
        double minDistance = 0.0;
        if (end < start + 2) {
            return 0.0;
        }
        double distance1 = Math.sqrt(Math.pow((points.get(start).getxPos() - points.get(start).getyPos()), 2) + Math.pow((points.get(end).getxPos() - points.get(end).getyPos()), 2));
        double distance2 = Math.sqrt(Math.pow((points.get(start).getxPos() - points.get(start).getyPos()), 2) + Math.pow((points.get(end - 1).getxPos() - points.get(end - 1).getyPos()), 2));
        if (distance1 < distance2) {
            pointsLeft.remove(new Point(points.get(start).getxPos(), points.get(start).getyPos()));
            pointsLeft.remove(new Point(points.get(end).getxPos(), points.get(end).getyPos()));
            //remove final point of triangle
        }
        else {
            pointsLeft.remove(new Point(points.get(start).getxPos(), points.get(start).getyPos()));
            pointsLeft.remove(new Point(points.get(end-1).getxPos(), points.get(end-1).getyPos()));
            //remove final point of triangle
        }
        //left side
        greedy(points, 0,0);
        //right side
        greedy(points, 0,0);

        return minDistance;
    }

    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList();
        GreedyMethod.greedy(points, 0, points.size());
    }
    
}
