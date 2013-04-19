package GameEngine.Objects.Buttons;

import android.graphics.Color;
import android.graphics.RectF;
import GameEngine.Core.GraphicsHandler.Renderer;

public class TextButtons extends BoxButtons {

	/**
	 * @uml.property  name="text"
	 */
	public String text;
	/**
	 * @uml.property  name="textColor"
	 */
	public int textColor;
	/**
	 * @uml.property  name="textPressedColor"
	 */
	public int textPressedColor;
	public TextButtons(){
		super();
		text = "";
		this.textColor = Color.BLACK;
		this.textPressedColor = Color.WHITE;
	}
	public TextButtons(float x, float y, String text){
		this.text = text;
		this.x = x;
		this.y = y;
		this.Width = (int) Renderer.TextWidth(text);
		this.Height = (int) Renderer.TextHeight(text);
		this.color = Color.WHITE;
		this.pressedColor = Color.BLACK;
		this.textColor = Color.BLACK;
		this.textPressedColor = Color.WHITE;
	}
	public TextButtons(float x, float y, int Width, int Height, String text){
		super(x, y, Width, Height);
		this.text = text;
		this.color = Color.WHITE;
		this.pressedColor = Color.BLACK;
		this.textColor = Color.BLACK;
		this.textPressedColor = Color.WHITE;
	}
	@Override
	public void Draw(Renderer r) {
		if(text != null){
			super.Draw(r);
			if(mPressed){
				r.DrawText(x, y, text, textPressedColor);
			} else {
				r.DrawText(x, y, text, textColor);
			}
		}
	}
	@Override
	public RectF BoundingBox() {
		if(text != null && Width == 0 && Height == 0){
			this.Width = (int) Renderer.TextWidth(text);
			this.Height = (int) Renderer.TextHeight(text);
		}
		return super.BoundingBox();
	}
}
