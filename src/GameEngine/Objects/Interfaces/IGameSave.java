package GameEngine.Objects.Interfaces;

public interface IGameSave {
	
	public void Setup();
	public void EditSave(String Data);
	public void SaveData();
	public void LoadSave(String Filename);
	public void Delete();
	public boolean Edited();
	public boolean IsLoaded();
	
}
