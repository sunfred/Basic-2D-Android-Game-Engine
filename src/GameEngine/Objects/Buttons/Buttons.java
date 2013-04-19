package GameEngine.Objects.Buttons;

import GameEngine.Core.GraphicsHandler.Renderer;
import GameEngine.Core.InputHandler.Controls;
import GameEngine.Objects.Object;

public abstract class Buttons extends Object {
	public Buttons(){
		super();
	}
	public Buttons(float x, float y){
		super(x, y);
	}
	@Override
	public abstract boolean Contains(float x, float y);
	@Override
	public abstract void Draw(Renderer r);
	public boolean isPressed(Controls c){
		if(Contains(c.getPoint().x, c.getPoint().y) && c.isTouched()){
			mPressed = true;
			return true;
		}
		mPressed = false;
		return false;
	}
	/**
	 * @uml.property  name="mPressed"
	 */
	protected boolean mPressed;	
}
