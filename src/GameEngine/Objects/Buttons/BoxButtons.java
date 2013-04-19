package GameEngine.Objects.Buttons;

import android.graphics.Color;
import android.graphics.RectF;
import GameEngine.Core.GraphicsHandler.Renderer;
import GameEngine.Objects.Interfaces.IBox;

public class BoxButtons extends Buttons implements IBox {

	public int Width;
	public int Height;
	public int color;
	public int pressedColor;
	// Public Methods
	public BoxButtons(){
		super();
		Width = Height = 0;
		color = Color.WHITE;
		pressedColor = Color.RED;
	}
	public BoxButtons(float x, float y, int Width, int Height){
		super(x, y);
		this.Width = Width;
		this.Height = Height;
		color = Color.WHITE;
		pressedColor = Color.RED;
	}
	public BoxButtons(float x, float y, int Width, int Height, int color, int pressedColor){
		super(x, y);
		this.Width = Width;
		this.Height = Height;
		this.color = color;
		this.pressedColor = pressedColor;
	}
	@Override
	public void Draw(Renderer r){
		if(mPressed){
			r.DrawBlock(x, y, Width, Height, pressedColor);
		} else {
			r.DrawBlock(x, y, Width, Height, color);
		}
	}
	@Override
	public String toString() {
		return "Pos: " + x + "," + y + " \nDim: " + Width + "," + Height;  
	}
	@Override
	public boolean Contains(float x, float y) {
		if(BoundingBox().contains(x, y)){
			return true;
		}
		return false;
	}
	public RectF BoundingBox() {
		return new RectF(x, y, x + Width, y + Height);
	}
}
