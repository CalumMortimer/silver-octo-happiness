package com.example.sortvisualiserpro;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;

public class SelectionSort {
    private Integer[] values = {1,11,16,7,10,14,2,13,12,4,5,9,19,17,3,6,15,8,0,18};
    private int targetValue = 0;
    private int currentValue = 0;
    private int lowestScore = 0;
    private boolean isSolved = false;

    /** Create a Selection Sorting object. On creation, the array is randomised*/
    SelectionSort()
    {
        shuffle();
    }

    /**Return a value at an index of the array*/
    public int getAValue(int index)
    {
        return values[index];
    }

    /**Return the location of the targetValue*/
    public int getTargetValue()
    {
        return targetValue;
    }

    /**Return the location of the currentValue*/
    public int getCurrentValue() { return currentValue; }

    /**Return the value of the lowestScore*/
    public int getLowestScore() { return lowestScore; }

    /**Return if the array has been solved*/
    public boolean getIsSolved(){return isSolved;}

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
        if ((values[currentValue]<values[targetValue])&&values[currentValue]<values[lowestScore])
        {
            lowestScore = currentValue;
        }
        currentValue++;
        if (currentValue==values.length)
        {
            passComplete();
        }
    }

    /**Perform the logic required after a pass of the array*/
    private void passComplete()
    {
        int temp = values[targetValue];
        values[targetValue] = values[lowestScore];
        values[lowestScore] = temp;

        targetValue++;
        if (targetValue==20)
        {
            isSolved = true;
        }

        currentValue = targetValue;
        lowestScore = targetValue;
    }
}
