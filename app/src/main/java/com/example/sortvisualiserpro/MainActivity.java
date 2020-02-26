package com.example.sortvisualiserpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
   /* public static final String BUBBLE_ADDRESS = "com.example.sortvisualiserpro.BUBBLE";
    public static final String SELECTION_ADDRESS = "com.example.sortvisualiserpro.SELECTION";
    public static final String INSERTION_ADDRESS = "com.example.sortvisualiserpro.INSERTION";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**Enters the Bubble Screen*/
    public void enterBubble(View view) {
        Intent intent = new Intent(this, BubbleScreenActivity.class);
        startActivity(intent);
    }

    /**Enters the Selection Screen*/
    public void enterSelection(View view){
        Intent intent = new Intent(this, SelectionScreenActivity.class);
        startActivity(intent);
    }

    /**Enters the Insertion Screen*/
    public void enterInsertion(View view){
        Intent intent = new Intent(this, InsertionScreenActivity.class);
        startActivity(intent);
    }
}
