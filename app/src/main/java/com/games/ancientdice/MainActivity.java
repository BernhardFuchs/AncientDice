package com.games.ancientdice;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Map;


public class MainActivity extends Activity implements GestureDetector.OnGestureListener ,GestureDetector.OnDoubleTapListener{

    public static final int DICETRUE = R.drawable.dicetrue;
    public static final int DICEFALSE = R.drawable.dicefalse;
    private ImageView dice0;
    private ImageView dice1;
    private ImageView dice2;
    private ImageView dice3;
    private ImageView diceView;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerDiceViews();

        mDetector = new GestureDetectorCompat(this, this);
        mDetector.setOnDoubleTapListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Double-Tap to roll the dices", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void registerDiceViews() {
        dice0 = new ImageView(this);
        dice0 =(ImageView) findViewById(R.id.Dice0);
        dice0.setImageResource(DICEFALSE);

        dice1 = new ImageView(this);
        dice1 =(ImageView) findViewById(R.id.Dice1);
        dice1.setImageResource(DICEFALSE);

        dice2 = new ImageView(this);
        dice2 =(ImageView) findViewById(R.id.Dice2);
        dice2.setImageResource(DICEFALSE);

        dice3 = new ImageView(this);
        dice3 =(ImageView) findViewById(R.id.Dice3);
        dice3.setImageResource(DICEFALSE);

    }

    private void resetDiceViews(){
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        this.mDetector.onTouchEvent(e);
        return super.onTouchEvent(e);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static final String DEBUG_TAG = "Gestures";
    private static final String CUSTOM_APP_TAG = "AncientDiceApp";

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        try {
            Thread.sleep(200l);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        runDice(e);
        return true;
    }

    private void runDice(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        Log.i(CUSTOM_APP_TAG, "dice roll started!");
        DiceService dice = new DiceService();
        Map<Integer, Boolean> resultTable = dice.roll();

        processResult(resultTable);

        Log.i(CUSTOM_APP_TAG, "dice roll ended successfully!");
    }

    private void fadeOutDiceViews() {
        dice0.animate().alpha(0f).setDuration(1000l);
        dice1.animate().alpha(0f).setDuration(1000l);
        dice2.animate().alpha(0f).setDuration(1000l);
        dice3.animate().alpha(0f).setDuration(1000l);
    }

    private void fadeInDiceViews() {
        dice0.animate().alpha(1f).setDuration(1000l);
        dice1.animate().alpha(1f).setDuration(1000l);
        dice2.animate().alpha(1f).setDuration(1000l);
        dice3.animate().alpha(1f).setDuration(1000l);
    }

    private void processResult(Map<Integer, Boolean> resultTable) {
        if (resultTable.get(0)){
            dice0.setImageResource(DICETRUE);
        } else {
            dice0.setImageResource(DICEFALSE);
        }

        if (resultTable.get(1)){
            dice1.setImageResource(DICETRUE);
        } else {
            dice1.setImageResource(DICEFALSE);
        }

        if (resultTable.get(2)){
            dice2.setImageResource(DICETRUE);
        } else {
            dice2.setImageResource(DICEFALSE);
        }

        if (resultTable.get(3)){
            dice3.setImageResource(DICETRUE);
        } else {
            dice3.setImageResource(DICEFALSE);
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
