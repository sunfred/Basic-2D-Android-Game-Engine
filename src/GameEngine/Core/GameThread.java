package GameEngine.Core;


import android.util.Log;

public class GameThread extends Thread {
	// Public Methods
	public GameThread(GameEngine Sim){
		super();
		mSimulator = Sim;
	}
	public void SetRunning(boolean value){
		isRunning = value;
	}
	@Override
	public void run() {
		while(isRunning){
				if(mSimulator != null){
					mSimulator.GameLoop();
			}
		}
	}
	public void Delay(long frameStartTime, long presetDelayTime) {
		long frameProcessingTime = (System.nanoTime() - frameStartTime) / 1000;
		if((float)frameProcessingTime < presetDelayTime){
			try {
				sleep(presetDelayTime - frameProcessingTime);
			} catch (InterruptedException e) {
				Log.d("Thread Delay", e.getMessage());
			}
		}
	}
	// Private Properties
	private GameEngine mSimulator;
	private boolean isRunning;
}
