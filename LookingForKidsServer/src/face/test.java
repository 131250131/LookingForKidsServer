package face;

import static org.bytedeco.javacpp.opencv_contrib.createLBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_highgui.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_highgui.imread;

import org.bytedeco.javacpp.opencv_contrib.FaceRecognizer;
import org.bytedeco.javacpp.opencv_core.Mat;


public class test {
	public static void main(String[] args){
		FaceRecognization faceRecognization = new FaceRecognization();
//		faceRecognization.train("./res/recognizer/train/");
		System.out.println("Training completed.");
		test t = new test();
		t.recognize("./res/recognizer/test/test.jpg", "./res/recognizer/trained.xml");
	}
	
	public void recognize(String testImagePath, String dataSourcePath) {
		Mat image = imread(testImagePath, CV_LOAD_IMAGE_GRAYSCALE);
		FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();
		faceRecognizer.load(dataSourcePath);
		int[] result = new int[100];
		double[] dl = new double[100];
		if((image.rows()>0)&&(image.cols()>0)){
			faceRecognizer.predict(image, result, dl);
		}else{
			System.out.println("Somthing wrong");
		}
		for(Integer i : result){
				System.out.println("int参数列表  "+i+"   double:"+dl[i]);
		}
	}
}
