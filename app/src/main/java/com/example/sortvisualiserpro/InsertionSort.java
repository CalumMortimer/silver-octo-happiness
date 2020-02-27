package com.example.sortvisualiserpro;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**A class representing an insertion sorting algorithm.
 * values - array of integers holding the numbers to be sorted
 * nextUnsorted - the location of the next number to be sorted into the list
 * maxCount - the fraction of the list which remains unsorted
 * makeRed - boolean which indicates if the GUI should highlight identified unsorted cells red
 * returnToYellow - boolean which indicates if the GUI should highlight just-sorted cells yellow
 * isSolved - boolean representing if the list has been sorted
 * swapMadeThisCycle - boolean representing if a swap has been made during the current pass
 */
public class InsertionSort{
    private Integer[] values = {1,11,16,7,10,14,2,13,12,4,5,9,19,17,3,6,15,8,0,18};
    private int nextUnsorted = 1;
    private int currentPosition = 1;
    private int highLightCell1 = 0;
    private int highLightCell2 = 0;
    private boolean makeRed = false;
    private boolean returnToYellow = false;
    private boolean isSolved = false;
    private boolean slotFound = false;

    /** Create a Bubble Sorting object. On creation, the array is randomised*/
    InsertionSort()
    {
        shuffle();
    }

    /**Return a value at an index of the array*/
    public int getAValue(int index)
    {
        return values[index];
    }

    /**Return the location of the first number being considered for swapping*/
    public int getCurrentPosition()
    {
        return currentPosition;
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

    /**Perform an iteration of the sorting algorithm.*/
    public void iterate()
    {
        //if (returnToYellow)
        //{
          //  returnToYellow = false;
       // }
        if (makeRed)
        {
            int temp = values[currentPosition];
            values[currentPosition]=values[currentPosition-1];
            values[currentPosition-1] = temp;
            makeRed = false;
            //returnToYellow = true;
            currentPosition--;
        }
        else if (currentPosition>=1&&!slotFound) {
            if (values[currentPosition] < values[currentPosition - 1]) {
                makeRed = true;
            }
            else
            {
                slotFound=true;
            }
        }
        else
        {
            nextUnsorted++;
            currentPosition = nextUnsorted;
            slotFound = false;
            if (nextUnsorted == 20)
            {
                isSolved = true;
            }
        }
    }
}
