package com.example.sortvisualiserpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.CountDownTimer;

/**A class representing the bubble sort screen page
 * A bubble sort object is instantiated from the BubbleSort class
 * The textViews on the activity screen are stored as a 20 element array*/
public class BubbleScreenActivity extends AppCompatActivity {

    BubbleSort myBubbleSort = new BubbleSort();
    int[] myViewArray = {R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,
            R.id.textView5,R.id.textView6,R.id.textView7,R.id.textView8,R.id.textView9,
            R.id.textView10,R.id.textView11,R.id.textView12,R.id.textView13,R.id.textView14,
            R.id.textView15,R.id.textView16,R.id.textView17,R.id.textView18,R.id.textView19,
            R.id.textView20};

    /**Initiates the activity, sets the page title to "BUBBLE SORT" and refreshes the views from
     * the BubbleSort object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_screen);
        TextView pageTitle = (TextView)findViewById(R.id.textView);
        pageTitle.setText("BUBBLE SORT");
        refreshView();
    }

    /**"paints" the 20 textViews on the activity a particular colour*/
    private void paintWholeArray(int color)
    {
        for (int i=0;i<20;i++)
        {
            View myView = findViewById(myViewArray[i]);
            TextView myTextView = (TextView) myView;
            myTextView.setText(Integer.toString(myBubbleSort.getAValue(i)));
            myTextView.setBackgroundColor(color);
        }
    }

    private void paintCell(int color,int index)
    {
        View myView = findViewById(myViewArray[index]);
        TextView myTextView = (TextView) myView;
        myTextView.setBackgroundColor(color);
    }

    private void refreshView()
    {
        paintWholeArray(0xFFFFFFFF);

        if (myBubbleSort.getIsSolved())
        {
            paintWholeArray(0xFF00FF00);
        }
        else if (myBubbleSort.getMakeRed()) {
            paintCell(0xFFFF0000,myBubbleSort.getCompare1());
            paintCell(0xFFFF0000,(myBubbleSort.getCompare1()+1));
        }
        else
        {
            paintCell(0xFFFFFF00,myBubbleSort.getCompare1());
            paintCell(0xFFFFFF00,(myBubbleSort.getCompare1()+1));
        }
    }

    public void bubbleStep(View view)
    {
        myBubbleSort.iterate();
        refreshView();
    }

    private void autoStep()
    {
        myBubbleSort.iterate();
        refreshView();
    }

    public void autoBubble(View view) {
        new CountDownTimer(400000,200)
        {
            public void onTick(long interval)
            {
                autoStep();
                if (myBubbleSort.getIsSolved()) {
                    cancel();
                }
            }
            public void onFinish()
            { }
        }.start();
    }
}

