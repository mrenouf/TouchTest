package com.example.mrenouf.touchtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by mrenouf on 1/24/18.
 */

public class TouchTracingFrameLayout extends FrameLayout {
  final String name;

  public TouchTracingFrameLayout(@NonNull Context context) {
    this(context, null, 0);
  }

  public TouchTracingFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TouchTracingFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    if (getId() != View.NO_ID) {
      name = context.getResources().getResourceEntryName(getId());
    } else {
      name = "<NO_ID>";
    }
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    Log.d(name, "-> onInterceptTouchEvent (" + ev.toString() + ")");
    boolean intercepted = super.onInterceptTouchEvent(ev);
    Log.d(name, "<- onInterceptTouchEvent (" + intercepted + ")");
    return intercepted;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.d(name, "-> onTouchEvent (" + event.toString() + ")");
    boolean handled = super.onTouchEvent(event);
    Log.d(name, "<- onTouchEvent (" + handled + ")");
    return  handled;
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    Log.d(name, "-> dispatchTouchEvent (" + ev.toString() + ")");
    boolean handled = super.dispatchTouchEvent(ev);
    Log.d(name, "<- dispatchTouchEvent (" + handled + ")");
    return handled;
  }
}
