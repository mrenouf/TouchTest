package com.example.mrenouf.touchtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;


public class MainActivity extends Activity {
  private static final String TAG = "MainActivity";

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getActionMasked() != MotionEvent.ACTION_MOVE) {
      Log.d(TAG, "-> dispatchTouchEvent (" + ev.toString() + ")");
    }
    boolean handled = super.dispatchTouchEvent(ev);
    if (ev.getActionMasked() != MotionEvent.ACTION_MOVE) {
      Log.d(TAG, "<- dispatchTouchEvent (" + handled + ")");
    }
    return handled;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.root).setOnClickListener((v) ->
        Log.d("root", "clicked")
    );

    findViewById(R.id.a).setOnClickListener((v) ->
        Log.d("a", "clicked")
    );

  }
}
