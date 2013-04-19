package GameEngine.Objects.Buttons;

import GameEngine.Core.GraphicsHandler.Renderer;
import android.graphics.Bitmap;
import android.graphics.RectF;

public class ImageButtons extends BoxButtons {

	// Public Properties
	/**
	 * @uml.property  name="image"
	 * @uml.associationEnd  
	 */
	public Bitmap Image;
	// Public Methods
	public ImageButtons() {
		super();
		Image = null;
	}
	public ImageButtons(float x, float y, Bitmap Image) {
		this.x = x;
		this.y = y;
		this.Width = Image.getWidth();
		this.Height = Image.getHeight();
		this.Image = Image;
	}
	@Override
	public void Draw(Renderer r){
		if(Image != null){
			if(mPressed){
				r.DrawImage(x, y, Image, pressedColor);
			} else {
				r.DrawImage(x, y, Image, color);
			}
		}
	}
	@Override
	public RectF BoundingBox() {
		if(Image != null && Width == 0 && Height == 0){
			Width = Image.getWidth();
			Height = Image.getHeight();
		}
		return super.BoundingBox();
	}
}
