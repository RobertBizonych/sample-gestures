package com.authorwjf.gesture;

import android.app.Activity;
import android.gesture.*;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main extends Activity implements GestureOverlayView.OnGesturePerformedListener, GestureOverlayView.OnGestureListener {
	private GestureLibrary library;
	private GestureDetector gDetector;
    private Toast toast;
    private EditText nameField;
    private String gestureName;
    private GestureOverlayView gestures;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        gDetector = new GestureDetector(this);
        library = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!library.load()) {
            finish();
        }

        gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGestureListener(this);
        gestures.addOnGesturePerformedListener(this);

        nameField = (EditText) findViewById(R.id.gesture_name);
        gestureName = nameField.getText().toString();
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
        Button saveGesture = (Button)findViewById(R.id.save_gesture);
        saveGesture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                library.addGesture(gestureName, gesture);
                Toast.makeText(Main.this, gestureName + " added", Toast.LENGTH_LONG).show();
            }
        });

//        if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
//            String result = predictions.get(0).name;
//            if ("peru".equalsIgnoreCase(result)) {
//                Toast.makeText(this, "Peru", Toast.LENGTH_LONG).show();
//            } else if ("save".equalsIgnoreCase(result)) {
//                Toast.makeText(this, "Saving the document", Toast.LENGTH_LONG).show();
//            }
//        }
    }

    @Override
    public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onGesture(GestureOverlayView overlay, MotionEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}