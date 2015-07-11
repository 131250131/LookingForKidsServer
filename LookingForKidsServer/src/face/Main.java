package face;

public class Main {

	public static void main(String[] args) {
		FaceRecognization faceRecognization = new FaceRecognization();
		faceRecognization.train("./res/recognizer/train/");
		faceRecognization.recognize("./res/recognizer/test/test.jpg", "./res/recognizer/trained.xml");
	}

}
