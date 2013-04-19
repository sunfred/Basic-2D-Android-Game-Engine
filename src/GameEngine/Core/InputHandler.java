package GameEngine.Core;

import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;

public class InputHandler {
	// Public Methods
	public InputHandler(){
		mControls = new Controls();
		mAction = 0;
		x1 = x2 = y1 = y2 = 0;
	}
	public void ProcessInput(MotionEvent ev) {
		mAction = ev.getAction();
		switch(mAction)
		{
			case MotionEvent.ACTION_DOWN:
				x1 = x2 = ev.getX();
				y1 = y2 = ev.getY();
				IsTouched = true;
				break;
			case MotionEvent.ACTION_MOVE:
				x2 = ev.getX();
				y2 = ev.getY();
				break;
			case MotionEvent.ACTION_UP:
				IsTouched = false;
				break;
		}		
		//dumpEvent(ev);
	}
	@SuppressWarnings("unused")
	private void dumpEvent(MotionEvent event) {
		String names[] = { "DOWN" , "UP" , "MOVE" , "CANCEL" , "OUTSIDE" ,
		"POINTER_DOWN" , "POINTER_UP" , "7?" , "8?" , "9?" };
		StringBuilder sb = new StringBuilder();
		int action = event.getAction();
		int actionCode = action & MotionEvent.ACTION_MASK;
		sb.append("event ACTION_" ).append(names[actionCode]);
		if (actionCode == MotionEvent.ACTION_POINTER_DOWN
		|| actionCode == MotionEvent.ACTION_POINTER_UP) {
		sb.append("(pid " ).append(
		action >> MotionEvent.ACTION_POINTER_INDEX_SHIFT);
		sb.append(")" );
		}
		sb.append("[" );
		for (int i = 0; i < event.getPointerCount(); i++) {
		sb.append("#" ).append(i);
		sb.append("(pid " ).append(event.getPointerId(i));
		sb.append(")=" ).append((int) event.getX(i));
		sb.append("," ).append((int) event.getY(i));
		if (i + 1 < event.getPointerCount())
		sb.append(";" );
		}
		sb.append("]" );
		sb.append("Press Time:" + mControls.getPressTime());
		Log.d(TAG, sb.toString());
		}
	public Controls Controls(){
		UpdateInputs();
		return mControls;
	}
	private void UpdateInputs() {
		if(IsTouched){
			mPressTime ++;
		} else {
			mPressTime = 0;
		}
	}
	// Private Properties
	/**
	 * @uml.property  name="mControls"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="this$0:GameEngine.Core.InputHandler$Controls"
	 */
	private Controls mControls;	
	/**
	 * @uml.property  name="mAction"
	 */
	private int mAction;
	/**
	 * @uml.property  name="mPressTime"
	 */
	private int mPressTime;
	/**
	 * @uml.property  name="x1"
	 */
	private float x1;
	/**
	 * @uml.property  name="y1"
	 */
	private float y1;
	/**
	 * @uml.property  name="x2"
	 */
	private float x2;
	/**
	 * @uml.property  name="y2"
	 */
	private float y2;
	/**
	 * @uml.property  name="isTouched"
	 */
	private boolean IsTouched;
	private static final String TAG = InputHandler.class.getSimpleName();
	// Private Methods
	
	public class Controls {
		// Public Methods
		public Controls(){
			MinDif = 50.0f;
			mSwipeAmount = 0;
			mPoint = new PointF();
		}
		public PointF getPoint(){
			mPoint.x = x2;
			mPoint.y = y2;
			return mPoint;
		}
		public int getSwipeAmount(){
			return mSwipeAmount;
		}
		public int getPressTime(){
			return mPressTime;
		}
		public boolean isTouched() {
			return IsTouched;
		}
		public boolean swipeUp(){
			if(y2 + MinDif < y1){
				Log.d(TAG, "Swipe up");
				mSwipeAmount = (int) (y2 - y1);
				Reset();
				return true;
			}
			return false;
		}		
		public boolean swipeDown(){
			if(y2 > y1 + MinDif){
				Log.d(TAG, "Swipe down");
				mSwipeAmount = (int) (y2 - y1);
				Reset();
				return true;
			}
			return false;
		}		
		public boolean swipeLeft(){
			if(x2 + MinDif < x1){
				Log.d(TAG, "Swipe left");
				mSwipeAmount = (int) (x2 - x1);
				Reset();
				return true;
			}
			return false;
		}		
		public boolean swipeRight(){
			if(x2 > x1 + MinDif){
				Log.d(TAG, "Swipe right");
				mSwipeAmount = (int) (x2 - x1);
				Reset();
				return true;
			}
			return false;
		}
		public void setSwipeDistance(int Value) {
			mSwipeAmount	= Value;		
		}	
		public void Reset(){
			x1 = x2;
			y1 = y2;
			mPressTime = 0;
		}
		// Private Properties
		private int mSwipeAmount;
		private float MinDif;
		private PointF mPoint;

			
	}
}
