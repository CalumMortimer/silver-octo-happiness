package com.example.sortvisualiserpro;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**A class representing a bubble sorting algorithm.
 * values array represents the numbers to be sorted
 * compare1 represents the current position being considered in the list
 * maxCount represents how much of the array is currently unsorted
 * makeRed represents when cells should be highlighted red (unsorted values found)
 * returnToYellow repesents the state after the cells have been swapped around (after makeRed)
 * isSolved represents when the values array has been sorted
 * swapMadeThisCycle represents if a swap has occurred through the current loop of the array,
 * if none is detected, the list hsa been sorted.
 */
public class BubbleSort{
    private Integer[] values = {1,11,16,7,10,14,2,13,12,4,5,9,19,17,3,6,15,8,0,18};
    private int compare1 = 0;
    private int maxCount = values.length;
    private boolean makeRed = false;
    private boolean returnToYellow = false;
    private boolean isSolved = false;
    private boolean swapMadeThisCycle = false;

    /** Create a Bubble Sorting object. On creation, the array is randomised*/
    BubbleSort()
    {
        shuffle();
    }

    /**Return a value at an index of the array*/
    public int getAValue(int index)
    {
        return values[index];
    }

    /**Return the location of the first number being considered*/
    public int getCompare1()
    {
        return compare1;
    }

    /**Return the value of the makeRed boolean*/
    public boolean getMakeRed() { return makeRed; }

    /**Return the value of the isSolved boolean*/
    public boolean getIsSolved() { return isSolved; }

    /**Randomise the values array*/
    private void shuffle()
    {
        List<Integer> l = Arrays.asList(values);
        Collections.shuffle(l);
        for (int i=0;i<values.length;i++)
        {
            values[i] = l.get(i);
        }
    }

    /**perform an iteration of the sorting algorithm*/
    public void iterate()
    {
        //First mode of iteration - no out of order cells found yet
        if (!(makeRed||returnToYellow))
        {
            checkValues();
        }
        //Second mode of iteration - cells have been made red and should be swapped
        else if (makeRed)
        {
            makeRed = false;
            returnToYellow = true;
            swapMadeThisCycle = true;
            int temp = values[compare1];
            values[compare1] = values[compare1+1];
            values[compare1+1] = temp;
        }
        //Third mode of iteration - cells have been returned to yellow and it's time to move on
        else if (returnToYellow)
        {
            returnToYellow = false;
            compare1++;
            if (compare1 >= (maxCount - 1)) {
                compare1 = 0;
                //Every loop of the list, maxCount decreases by 1
                maxCount--;
                //List solved when maxCount == 1
                if ((maxCount == 1)||(!swapMadeThisCycle)) {
                    isSolved = true;
                }
                swapMadeThisCycle = false;
            }
        }
    }

    /**Makes cells go RED if they need to be swapped
     * or moves along the list otherwise
     */
    private void checkValues()
    {
        //Out of order cells found - highlight them RED
        if (values[compare1]>values[compare1+1])
        {
            makeRed = true;
        }
        //No out of order cells found
        else
        {
            //Move along the list
            compare1++;
            //Reset compare1 if out of unsorted scope
            if (compare1>=(maxCount-1))
            {
                compare1 = 0;
                //Every loop of the list, maxCount decreases by 1
                maxCount--;
                //List solved when maxCount == 1
                if ((maxCount==1)||(!swapMadeThisCycle))
                {
                    isSolved = true;
                }
                swapMadeThisCycle = false;
            }
        }
    }
}
