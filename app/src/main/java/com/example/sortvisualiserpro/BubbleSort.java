package com.example.sortvisualiserpro;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**A class representing a bubble sorting algorithm.
 * values - array of integers holding the numbers to be sorted
 * compare1 - the current position of the sort in the list
 * maxCount - the fraction of the list which remains unsorted
 * makeRed - boolean which indicates if the GUI should highlight identified unsorted cells red
 * returnToYellow - boolean which indicates if the GUI should highlight just-sorted cells yellow
 * isSolved - boolean representing if the list has been sorted
 * swapMadeThisCycle - boolean representing if a swap has been made during the current pass
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

    /**Return the location of the first number being considered for swapping*/
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

    /**Checks if the array has been solved. Method is called at the end of each pass of the array.
     * If no swaps have been made, the array is considered solved.
     */
    private void checkIfSolved()
    {
        if (!swapMadeThisCycle)
        {
            isSolved = true;
        }
    }

    /**Checks if the end of the unsorted portion of the array has been reached. If it has,
     * compare1 is set to 0 and the unsorted portion of the array is decreased by 1.
     * checkIfSolved is called to establish if the array has been fully sorted and
     * swapMadeThisCycle is reset to false.
     */
    private void endOfListCheck()
    {
        if (compare1 >= (maxCount - 1)) {
            compare1 = 0;
            //Every loop of the list, maxCount decreases by 1
            maxCount--;
            //List solved when no swap has been made this cycle
            checkIfSolved();
            swapMadeThisCycle = false;
        }
    }

    /**Perform an iteration of the sorting algorithm.*/
    public void iterate()
    {
        //First mode of iteration - no out of order cells found so far
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
            endOfListCheck();
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
            endOfListCheck();
        }
    }
}
