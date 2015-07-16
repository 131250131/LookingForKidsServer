package facerec;

public class TrainingController {
	private static TrainingController instance = null;
	private TrainingController(){
		
	}
	public TrainingController getInstance(){
		if(this.instance == null){
			instance = new TrainingController();
		}
		return this.instance;
	}
	
	public void train_All(){
		
	}
	
	public void train_OneMore(){
		
	}
	
	
	
}
