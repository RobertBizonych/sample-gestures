package com.practice.gestures;

import android.app.Activity;
import android.content.Intent;
import android.gesture.*;
import android.os.Bundle;
import android.os.Environment;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class GestureRecognizeActivity extends Activity implements GestureOverlayView.OnGesturePerformedListener {
	private GestureLibrary library;
    private GestureOverlayView gestures;
    private File libraryFile = new File(Environment.getExternalStorageDirectory(), "gestures");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        library = GestureLibraries.fromFile(libraryFile);
//        library.setOrientationStyle(GestureStore.ORIENTATION_INVARIANT);
//        library.setSequenceType(GestureStore.SEQUENCE_INVARIANT);
        if (!library.load()) {
            Toast.makeText(this, "Unable to load library", Toast.LENGTH_SHORT).show();
        }
        gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);
    }
    
//    @Override
//    public boolean onTouchEvent(MotionEvent me) {
//        return gDetector.onTouchEvent(me);
//    }
//
//	@Override
//	public boolean onDown(MotionEvent arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean onFling(MotionEvent start, MotionEvent finish, float xVelocity, float yVelocity) {
//		if (start.getRawY() < finish.getRawY()) {
//			((ImageView)findViewById(R.id.image_place_holder)).setImageResource(R.drawable.down);
//		} else {
//			((ImageView)findViewById(R.id.image_place_holder)).setImageResource(R.drawable.up);
//		}
//
//        if (start.getRawX() < finish.getRawX()) {
//            toast = Toast.makeText(this, "Left to right", Toast.LENGTH_LONG);
//            toast.show();
//        } else {
//            toast = Toast.makeText(this, "Right to left", Toast.LENGTH_LONG);
//            toast.show();
//        }
//		return true;
//	}
//
//	@Override
//	public void onLongPress(MotionEvent arg0) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
//			float arg3) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void onShowPress(MotionEvent arg0) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public boolean onSingleTapUp(MotionEvent arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, final Gesture gesture) {
        ArrayList<Prediction> predictions = library.recognize(gesture);
        for (Prediction prediction : predictions) {
            if (prediction.score > 1.0) {
                Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
                break;
            } else {
                Toast.makeText(this, "No gesture", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addAnother(View view) {
        Intent intent = new Intent(this, CreateGestureActivity.class);
        startActivity(intent);
    }

    public void toList(View view) {
        Intent intent = new Intent(this, GestureBuilderActivity.class);
        startActivity(intent);
    }
}