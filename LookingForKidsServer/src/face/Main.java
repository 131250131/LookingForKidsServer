package face;

public class Main {

	public static void main(String[] args) {
		FaceRecognition faceRecognization = new FaceRecognition();
		faceRecognization.train("./res/train/");
		System.out.println("Training completed.");
		faceRecognization.recognize("./res/test/test2.jpg", "./res/trained.xml");
	}

}
