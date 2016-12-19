package DynamicProgramming;

import java.util.Stack;

/**
 * Created by li on 7/13/2016.
 */
public class LargestRectangleInHistogram84 {

    public static void main(String[] args) {
        int[] array = {4,2,0,3,2,5};

        System.out.println(largestRectangleArea(array));
    }


    // 53%
    public static int largestRectangleArea(int[] heights) {

        if(heights == null || heights.length == 0) return 0;
        Stack<Integer> s = new Stack<>();
        int i = 0, max = 0, area = 0;
        int height = 0;
        for(; i < heights.length;) {
            if(s.isEmpty() || heights[i] >= heights[s.peek()]) s.push(i++);
            else {
                height = heights[s.pop()];
                if(s.isEmpty()) {
                    area = height * i;
                } else {
                    area = height * (i - s.peek() - 1);
                }
                if(area > max) max = area;
            }
        }

        while(!s.isEmpty()) {
            height = heights[s.pop()];
            if(s.isEmpty()) {
                area = height * i;
            } else {
                area = height * (i - s.peek() - 1);
            }
            if(area > max) max = area;
        }
        return max;
    }

    /***
     * Dec 16
     */
    public int largestRectangleArea1(int[] heights) {
        if(heights == null || heights.length == 0) return 0;
        int i = 0, maxArea = 0, area = 0;
        int height = 0;
        Stack<Integer> s = new Stack<>();
        for(; i < heights.length; i++) {
            if(s.isEmpty() || heights[i] >= heights[s.peek()]) s.push(i);
            else {

                while(!s.isEmpty() && heights[i] < heights[s.peek()]) {
                    height = heights[s.pop()];
                    if(s.isEmpty()) {
                        area = height * i;
                    } else {
                        area = height * (i - s.peek() - 1);
                    }
                    maxArea = Math.max(maxArea, area);
                }
                s.push(i);
            }
        }

        while(!s.isEmpty()) {
            height = heights[s.pop()];
            if(s.isEmpty()) {
                area = height * i;
            } else {
                area = height * (i - s.peek() -1);
            }
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

}
