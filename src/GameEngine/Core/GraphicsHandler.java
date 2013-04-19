package GameEngine.Core;


import GameEngine.Scene.IScene;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceHolder;

public class GraphicsHandler {
	// Public Methods
	public GraphicsHandler(){
		mBackgroundColor = Color.BLACK;
		mPaint = new Paint();
		mPaint.setTextSize(mDefaultTextSize);
		mRenderer = new Renderer();
	}
	public void Setup(RectF Viewport) {
		Renderer.Viewport = Viewport;		
	}
	public void DrawScene(SurfaceHolder SurfaceHolder, IScene Scene) {
		mCanvas = null;
		try{
			mCanvas = SurfaceHolder.lockCanvas(null);
			synchronized (SurfaceHolder) {
				if(mCanvas != null && Scene != null && mRenderer != null){
					if(Scene.IsActive()){
						mCanvas.drawColor(mBackgroundColor);
						Scene.DrawScene(mRenderer);
					}
				}
			}
		} finally {
			if (mCanvas != null){
				SurfaceHolder.unlockCanvasAndPost(mCanvas);
			}
		}
	}
	// Private Properties 
	private Renderer mRenderer;
	private static int mBackgroundColor;
	private static Canvas mCanvas;
	private static Paint mPaint;
	private static float mDefaultTextSize = 25.0f;
	
	public static class Renderer {
		public static RectF Viewport;
		// Public Properties
		public static int BlockWidth;
		public static int BlockHeight;
		// Public Methods
		public Renderer(){
			BlockWidth = BlockHeight = 48;
			Viewport = new RectF();
		}
		public void DrawBlock(float x, float y){
			mPaint.setColor(Color.WHITE);
			mCanvas.drawRect(x, y, x + BlockWidth, y + BlockHeight, mPaint);
		}
		public void DrawBlock(float x, float y, int Width, int Height) {
			mPaint.setColor(Color.WHITE);
			mCanvas.drawRect(x, y, x + Width, y + Height, mPaint);
		}
		public void DrawBlock(float x, float y, int Color){
			mPaint.setColor(Color);
			mCanvas.drawRect(x, y, x + BlockWidth, y + BlockHeight, mPaint);
		}
		public void DrawBlock(float x, float y, int Width, int Height, int Color) {
			mPaint.setColor(Color);
			mCanvas.drawRect(x, y, x + Width, y + Height, mPaint);
		}
		public void DrawCircle(float x, float y){
			mPaint.setColor(Color.WHITE);
			mCanvas.drawCircle(x, y, 5, mPaint);
		}
		public void DrawCircle(float x, float y, int radius){
			mPaint.setColor(Color.WHITE);
			mCanvas.drawCircle(x, y, radius, mPaint);
		}
		public void DrawCircle(float x, float y, float radius, int Color){
			mPaint.setColor(Color);
			mCanvas.drawCircle(x, y, radius, mPaint);
		}
		public void DrawText(float x, float y, String text){
			mPaint.setColor(Color.WHITE);
			mPaint.setTextSize(mDefaultTextSize);
			if(text != null){
				for(String line: text.split("\n")){
					mCanvas.drawText(line, x, y + 25, mPaint);
					y+= mPaint.getTextSize() + 2.0f;
				}
			}
		}
		public void DrawText(float x, float y, String text, int Color){
			mPaint.setColor(Color);
			mPaint.setTextSize(mDefaultTextSize);
			if(text != null){
				for(String line: text.split("\n")){
					mCanvas.drawText(line, x, y + 25, mPaint);
					y+= mPaint.getTextSize() + 2.0f;
				}
			}
		}
		public void DrawText(float x, float y, String text, float size, int Color){
			mPaint.setColor(Color);
			mPaint.setTextSize(mDefaultTextSize + size);
			if(text != null){
				for(String line: text.split("\n")){
					mCanvas.drawText(line, x, y + 25, mPaint);
					y+= mPaint.getTextSize() + 2.0f;
				}
			}
		}
		public void DrawImage(float x, float y, Bitmap b) {
			mPaint.setColor(Color.WHITE);
			if(b != null){
				mCanvas.drawBitmap(b, x, y, mPaint);
			}
		}
		public void DrawImage(float x, float y, Bitmap b, int Color) {
			if(b != null){
				Paint p = new Paint();
				p.setColorFilter(new LightingColorFilter(Color, 1));
				mCanvas.drawBitmap(b, x, y, p);
			}
		}
		public void DrawImage(float x, float y, Bitmap b, RectF Size) {
			mPaint.setColor(Color.WHITE);
			if(b != null){
				mCanvas.drawBitmap(b, new Rect((int)x, (int)y, (int)x + b.getWidth(), (int)y + b.getHeight()), Size, mPaint);
			}
		}
		public void DrawFill(float x, float y, float fillAmount){
			mPaint.setColor(Color.WHITE);
			mCanvas.drawRect(x, y, x + Viewport.right, y + fillAmount, mPaint);
		}
		public void DrawFill(float x, float y, float fillAmount, int Color){
			mPaint.setColor(Color);
			mCanvas.drawRect(x, y, x + Viewport.right, y + fillAmount, mPaint);
		}
		public void DrawGridOverlay(int x, int y){
			mPaint.setColor(Color.WHITE);
			for(int i = 0; i < Viewport.right / Renderer.BlockWidth; i++){
				mCanvas.drawLine(x + (i * Renderer.BlockWidth), y, x + (i * Renderer.BlockWidth), Viewport.bottom, mPaint);
			}
			for(int j = 0; j < Viewport.bottom / Renderer.BlockHeight; j++){
				mCanvas.drawLine(x, y + (j * Renderer.BlockWidth), Viewport.right, y + (j * Renderer.BlockWidth), mPaint);
			}
		}
		public void DrawGridOverlay(int x, int y, int color){
			mPaint.setColor(color);
			for(int i = 0; i < Viewport.right / Renderer.BlockWidth; i++){
				mCanvas.drawLine(x + (i * Renderer.BlockWidth), y, x + (i * Renderer.BlockWidth), Viewport.bottom, mPaint);
			}
			for(int j = 0; j < Viewport.bottom / Renderer.BlockHeight; j++){
				mCanvas.drawLine(x, y + (j * Renderer.BlockWidth), Viewport.right, y + (j * Renderer.BlockWidth), mPaint);
			}
		}
		public void SetBackground(int Color) {
			mBackgroundColor = Color;
		}
		public Canvas GetCanvas(){
			return mCanvas;
		}
		public static float TextWidth(String Text){
			mPaint.setTextSize(mDefaultTextSize);
			return mPaint.measureText(Text);
		}
		public static float TextWidth(String Text, float Size){
			mPaint.setTextSize(mDefaultTextSize + Size); 
			return mPaint.measureText(Text);
		}
		public static float TextHeight(String text){
			float height = 0.0f;
			for(@SuppressWarnings("unused") String line : text.split("\n")){
				height += mPaint.getTextSize() + 2.0f;
			}
			return height;
		}
		public static float TextHeight(String text, float Size){
			float height = 0.0f;
			for(@SuppressWarnings("unused") String line : text.split("\n")){
				height += mPaint.getTextSize() + Size + 2.0f;
			}
			return height;
		}
		public static void FlipBitmapVertical(Bitmap src){
			Matrix m = new Matrix(); 
			m.preScale(1.0f, -1.0f);
			src = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, true);
		}
		public static void FlipBitmapHorizontal(Bitmap src){
			Matrix m = new Matrix(); 
			m.preScale(-1.0f, 1.0f);
			src = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, true);
		}
	}
}