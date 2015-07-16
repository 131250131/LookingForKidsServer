package facerec;

public class GroupManager {
	private static GroupManager instance = null;
	private GroupManager(){}
	public static GroupManager getInstance(){
		if(instance == null){
			instance = new GroupManager();
		}
		return instance;
	}
	
	public void addSomePhoto(){
		
	}
	
	
	
}
