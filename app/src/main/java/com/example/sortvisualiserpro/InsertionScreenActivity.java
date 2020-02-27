package com.example.sortvisualiserpro;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InsertionScreenActivity extends AppCompatActivity {

    boolean isAutomated = false;
    InsertionSort myInsertionSort = new InsertionSort();
    int[] myViewArray = {R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,
            R.id.textView5,R.id.textView6,R.id.textView7,R.id.textView8,R.id.textView9,
            R.id.textView10,R.id.textView11,R.id.textView12,R.id.textView13,R.id.textView14,
            R.id.textView15,R.id.textView16,R.id.textView17,R.id.textView18,R.id.textView19,
            R.id.textView20};

    /**Initiates the activity, sets the page title to "INSERTION SORT" and refreshes the views from
     * the InsertionSort object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_screen);
        TextView pageTitle = (TextView)findViewById(R.id.textView);
        pageTitle.setText(R.string.insertion_sort);
        Button myManualButton = (Button)findViewById(R.id.button4);
        myManualButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                manualStep(v);
            }
        });
        Button myAutoButton = (Button)findViewById(R.id.button5);
        myAutoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                auto(v);
            }
        });

        refreshView();
    }

    /**"paints" the 20 textViews on the activity a particular colour*/
    private void paintWholeArray(int color)
    {
        for (int i=0;i<20;i++)
        {
            View myView = findViewById(myViewArray[i]);
            TextView myTextView = (TextView) myView;
            myTextView.setText(Integer.toString(myInsertionSort.getAValue(i)));
            myTextView.setBackgroundColor(color);
        }
    }

    /**Paints a textView object a particular colour*/
    private void paintCell(int color,int index)
    {
        View myView = findViewById(myViewArray[index]);
        TextView myTextView = (TextView) myView;
        myTextView.setBackgroundColor(color);
    }

    /**Refreshes (repaints) all of the textView objects on the activity*/
    private void refreshView()
    {
        paintWholeArray(0xFFFFFFFF);

        if (myInsertionSort.getIsSolved())
        {
            paintWholeArray(0xFF00FF00);
        }
        else if (myInsertionSort.getMakeRed()) {
            paintCell(0xFFFF0000,myInsertionSort.getCurrentPosition());
            if (myInsertionSort.getCurrentPosition()>=1){
                paintCell(0xFFFF0000,(myInsertionSort.getCurrentPosition()-1));}
        }
        else
        {
            paintCell(0xFFFFFF00,myInsertionSort.getCurrentPosition());
            if (myInsertionSort.getCurrentPosition()>=1){
            paintCell(0xFFFFFF00,(myInsertionSort.getCurrentPosition()-1));}
        }
    }

    /**Perform an iteration of the algorithm from a view element*/
    public void manualStep(View view)
    {
        if (!myInsertionSort.getIsSolved()) {
            myInsertionSort.iterate();
        }
        refreshView();
    }

    /**Perform an iteration of the algorithm (called from autoBubble)*/
    private void autoStep()
    {
        myInsertionSort.iterate();
        refreshView();
    }

    /**Automatically iterate through the algorithm. AUTO button toggles between AUTO and STOP
     * to allow the user to switch between manual and automatic modes*/
    public void auto(View view) {
        isAutomated = !isAutomated;
        Button thisButton = (Button)view;
        if (isAutomated)
        {
            thisButton.setText(R.string.stop_button);
        }
        else
        {
            thisButton.setText(R.string.auto_button);
        }

        new CountDownTimer(1000000,400)
        {
            public void onTick(long interval)
            {
                if (myInsertionSort.getIsSolved()) {
                    cancel();
                }
                else if (!isAutomated)
                {
                    cancel();
                }
                else {
                    autoStep();
                }
            }
            public void onFinish()
            { }
        }.start();
    }
}
