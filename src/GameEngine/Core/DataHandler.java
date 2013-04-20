package GameEngine.Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import GameEngine.Objects.Interfaces.IGameSave;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class DataHandler {
	// Public Properties
	// Public Methods
	public DataHandler(){
		mContext = null;
		mSettings = null;
		mEditor = null;
		mRootDirectory = null;
		mFileInpStream = null;
		mInpStream = null;
		mBuffReader = null;
		mOutStream = null;
		mOutStreamWriter = null;
	}
	public void Setup(Context c){
		mContext = c;
		mSettings = ((Activity)mContext).getSharedPreferences("" + mContext.getPackageName(), 0);
		mEditor = mSettings.edit();
		if(mRootDirectory == null){ 
			mRootDirectory = "Android/data/" + mContext.getPackageName() + "/Data";
			
			File mFile = new File(Environment.getExternalStorageDirectory() +"/" +  mRootDirectory);
			
			if(!mFile.exists()){
				mFile.mkdirs();
			}
			mFile = null;
		}
	}
	public void Shutdown(){
		if(mCurrentSave != null){
			if(mCurrentSave.Edited()){
				mCurrentSave.SaveData();
			}
		}
	}
	public static Bitmap LoadBitmap(int id) {
		Bitmap b = null;
		if(mContext != null){
			b = BitmapFactory.decodeResource(GetResource(), id);
		}
		return b;
	}
	public static String GetTextResource(int id) {
		mInpStream = null;
		mBuffReader = null;
		
		mInpStream = GetResource().openRawResource(id); 
		mBuffReader = new BufferedReader(new InputStreamReader(mInpStream));
		StringBuilder sb = new StringBuilder();
		String readLine = null;
		try{			
			while((readLine = mBuffReader.readLine()) != null){
				sb.append(readLine);
			}
			mInpStream.close();
			mBuffReader.close();
		} catch (IOException e){
			e.printStackTrace();
		}		
		
		return sb.toString();
	}
	public static void SaveFileData(String Filename, String Input){
		try {
			File mFile = new File(Environment.getExternalStorageDirectory() +"/" +  mRootDirectory + "/" + Filename);
			
			if(!mFile.exists()){
				mFile.createNewFile();
			}
			mOutStream = new FileOutputStream(mFile);
			mOutStreamWriter= new OutputStreamWriter(mOutStream);
			
			mOutStreamWriter.append(Input);
			
			mOutStreamWriter.close();
			mOutStream.close();
		} catch (Exception e){
		   e.printStackTrace();
		}
	}
	public static String LoadFileData(String Filename){
		mFileInpStream = null;
		mBuffReader = null;
		
		File mFile = new File(Environment.getExternalStorageDirectory()+ "/" + mRootDirectory + "/" + Filename);
		
		if(mFile.exists()){
			try {
				mFileInpStream = new FileInputStream(mFile);
				mBuffReader = new BufferedReader(new InputStreamReader(mFileInpStream));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		if(mFileInpStream != null){
			String str = "";
			StringBuilder sb = new StringBuilder();
			try {
				while((str = mBuffReader.readLine()) != null){
					sb.append(str + "\n");
				}
				mBuffReader.close();
				mFileInpStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return sb.toString();
		}
		return null;
	}
	public static boolean DeleteFileData(String Filename){
		File mFile = new File(Environment.getExternalStorageDirectory()+ "/" + mRootDirectory + "/" + Filename);
		if(mFile.exists()){
			return mFile.delete();
		}
		return false;
	}
	public static void SetCurrentGameSave(IGameSave Save){
		mCurrentSave = Save;
	}
	public static IGameSave GetCurrentSave(){
		return mCurrentSave;
	}
	
	public static void SaveSettingsIntData(String name, int value){
		mEditor.putInt(name, value);
		mEditor.commit();
		
		Log.d(TAG, "Saved int data: " + name + " " + value);
	}
	
	public static void SaveSettingsStringData(String name, String value){
		mEditor.putString(name, value);
		mEditor.commit();
		
		Log.d(TAG, "Saved string data: " + name + " " + value);
	}
	
	public static void SaveSettingsBooleanData(String name, boolean value){
		mEditor.putBoolean(name, value);
		mEditor.commit();
		
		Log.d(TAG, "Saved boolean data: " + name + " " + value);
	}
	
	public static int LoadSettingsIntData(String name){
		Log.d(TAG, "Loaded int data: " + name);
		return mSettings.getInt(name, 0);
	}
	
	public static String LoadSettingsStringData(String name){
		Log.d(TAG, "Loaded string data: " + name);
		return mSettings.getString(name, "");
	}
	
	public static boolean LoadSettingsBooleanData(String name){
		Log.d(TAG, "Loaded boolean data: " + name);
		return mSettings.getBoolean(name, false);
	}
	public static void DeleteSettingsData(String name){
		mEditor.remove(name);
		mEditor.commit();
	}
	public static void ClearSettingData(){
		mEditor.clear();
		mEditor.commit();
	}
	// Private Properties
	private static final String TAG = DataHandler.class.getSimpleName();
	private static Context mContext;
	private static SharedPreferences mSettings;
	private static SharedPreferences.Editor mEditor;
	private static String mRootDirectory;
	private static FileInputStream mFileInpStream;
	private static InputStream mInpStream;
	private static BufferedReader mBuffReader;
	private static OutputStream mOutStream;
	private static OutputStreamWriter mOutStreamWriter;
	private static IGameSave mCurrentSave;
	// Private Methods
	private static Resources GetResource(){
		if(mContext != null){
			return mContext.getResources();
		}
		return null;
	}
}
