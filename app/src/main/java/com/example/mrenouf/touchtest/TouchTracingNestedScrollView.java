package com.example.mrenouf.touchtest;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by mrenouf on 1/24/18.
 */

public class TouchTracingNestedScrollView extends NestedScrollView {
  private static Rect tmpRect = new Rect();
  private static Point tmpOffset = new Point();
  final String name;

  public TouchTracingNestedScrollView(@NonNull Context context) {
    this(context, null, 0);
  }

  public TouchTracingNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TouchTracingNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

  public View findTopChildUnder(ViewGroup parent, MotionEvent ev) {
    final int x = (int) (getLeft() + ev.getX());
    final int y = (int) (getTop() + ev.getY());
    final int childCount = parent.getChildCount();
    Log.d(name, "findTopChildUnder: " + x + ", " + y);
    for (int i = childCount - 1; i >= 0; i--) {
      final View child = parent.getChildAt(i); // getChildDrawingOrder
      Log.d(name, "test child: " + child);

      if (child.getLeft() <= x && child.getRight() > x && child.getTop() <= y && child.getBottom() > y) {
        Log.d(name, "match");
        return child;
      }
/*
      tmpOffset.set(getTop(), getLeft());
      parent.getChildVisibleRect(child, tmpRect, tmpOffset);
      Log.d(name, "child: " + child + " visibleRect: " + tmpRect.toShortString());
      if (tmpRect.contains(x, y)) {
        return child;
      }
*/
/*
      if (x >= child.getLeft() && x < child.getRight()
          && y >= child.getTop() && y < child.getBottom()) {
        return child;
      }
*/
    }
    return null;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    View top = findTopChildUnder(((ViewGroup) getParent()), event);

    if (top != this) {
      Log.d(name, "FILTER THIS EVENT");
      return false;
    }
    Log.d(name, "-> onTouchEvent (" + event.toString() + ")");

    boolean handled = super.onTouchEvent(event);
      Log.d(name, "<- onTouchEvent (" + handled + ")");
    return handled;
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
      Log.d(name, "-> dispatchTouchEvent (" + ev.toString() + ")");
    boolean handled = super.dispatchTouchEvent(ev);
      Log.d(name, "<- dispatchTouchEvent (" + handled + ")");
    return handled;
  }
}
