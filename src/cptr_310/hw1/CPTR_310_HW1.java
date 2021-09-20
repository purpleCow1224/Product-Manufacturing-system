/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cptr_310.hw1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 * @author yonaeldawit
 */
public class CPTR_310_HW1 {
    //Variables
    static Queue<Stack> processor = new Queue<Stack>();
    static Stack<Float> bin1 = new Stack<Float>();
    static Stack<Float> bin2 = new Stack<Float>();
    static Stack<Float> bin3 = new Stack<Float>();
    static Stack<Float> processingBin = new Stack<Float>();
    static float finishingTime;
    static int rodCount;
    static int removedBinCount;
    static int totalRodCount = 0;
    static float totalFinishingTime = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StdDraw.setXscale(0,100);
        StdDraw.setYscale(0,100);   

        processor.enqueue(bin1);
        processor.enqueue(bin2);
        processor.enqueue(bin3);
        for (int i = 0; i < 20; i++) {
            bin1.push((float) StdRandom.uniform(0.5, 1.0));
        }
        for (int i = 0; i < 15; i++) {
            bin2.push((float) StdRandom.uniform(1.0, 1.5));
        }
        for (int i = 0; i < 10; i++) {
            bin3.push((float) StdRandom.uniform(1.5, 2.0));
        }

        while (removedBinCount < 75 && rodCount <= 100 && binIsEmpty()) {
            finishingTime = 0;
            rodCount = 0;
            
            processingBin = processor.dequeue();
            removedBinCount++;
            rodCount = processingBin.size();
            totalRodCount += rodCount;
            
            while (!processingBin.isEmpty()) {
                finishingTime += processingBin.pop();
            }
            totalFinishingTime += finishingTime;
            addNewItems(finishingTime);
            processor.enqueue(processingBin);
            
            StdDraw.filledRectangle(20.0, 0.0, 5, bin1.size());
            StdDraw.filledRectangle(40.0, 0.0, 5, bin2.size());
            StdDraw.filledRectangle(60.0, 0.0, 5, bin3.size());
            StdDraw.show();
            StdDraw.pause(200);
            StdDraw.clear();    
        }
        
        System.out.println("Total rods processed: " + totalRodCount);
        System.out.println("Total finishing time: " + totalFinishingTime);
        System.out.println("Overall production rate: " + totalRodCount / secondsToMinutes(totalFinishingTime));
    }

    public static boolean binIsEmpty() {
        if (processor.peek().isEmpty()) {
            System.out.println("bin was empty!!");
            return false;
        }
        return true;
    }

    public static float secondsToMinutes(float seconds) {
        return seconds / (float) 60.0;
    }

    public static void addNewItems(float rodsPerMinute) {
        int newItems;

        newItems = Math.round(20 * secondsToMinutes(rodsPerMinute));
        for (int i = 0; i < newItems; i++) {
            bin1.push((float) StdRandom.uniform(0.5, 1.0));
        }

        newItems = Math.round(15 * secondsToMinutes(rodsPerMinute));
        for (int i = 0; i < newItems; i++) {
            bin2.push((float) StdRandom.uniform(1.0, 1.5));
        }

        newItems = Math.round(10 * secondsToMinutes(rodsPerMinute));
        for (int i = 0; i < newItems; i++) {
            bin3.push((float) StdRandom.uniform(1.5, 2.0));
        }
    }
}
