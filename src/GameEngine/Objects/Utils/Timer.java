package GameEngine.Objects.Utils;

import GameEngine.Utils;

public class Timer {
	
	public Timer(){
		milseconds = fps = frames = seconds = minutes = hours = 0;
	}
	
	public void Update(float t){
		if(this.milseconds > 999){
			UpdateTime();
		}
		this.milseconds += t;
		frames ++;
	}
	@Override
	public String toString() {
		return "FPS: " + fps + "\nTime Active: " + Utils.getNumberString(hours)
				+ ":" + Utils.getNumberString(minutes) + ":" + Utils.getNumberString(seconds)
				+ ":" + Utils.getNumberString(milseconds);
	}
	
	private void UpdateTime() {
		this.milseconds -= 1000;
		fps = frames;
		frames = 0;
		seconds++;
		if(seconds > 59){
			seconds -= 60;
			minutes++;
			if(minutes > 59){
				minutes -= 60;
				hours++;
			}
		}
	}

	/**
	 * @uml.property  name="fps"
	 */
	private int fps;
	/**
	 * @uml.property  name="frames"
	 */
	private int frames;
	/**
	 * @uml.property  name="milseconds"
	 */
	private int milseconds;
	/**
	 * @uml.property  name="seconds"
	 */
	private int seconds;
	/**
	 * @uml.property  name="minutes"
	 */
	private int minutes;
	/**
	 * @uml.property  name="hours"
	 */
	private int hours;
}
