package face;

import java.io.File;
import java.io.FilenameFilter;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.text.SimpleDateFormat;


import static org.bytedeco.javacpp.opencv_contrib.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.*;

public class FaceRecognization {

	private static final String TRAIN_RESULT_PATH = "./res/recognizer/trained.xml";
	
	/**
	 * 识别一张图片中的人
	 * @param testImagePath 要识别的图片的路径
	 * @param dataSourcePath 数据源的路径
	 */
	public void recognize(String testImagePath, String dataSourcePath) {
		Mat image = imread(testImagePath, CV_LOAD_IMAGE_GRAYSCALE);
		FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();	
		faceRecognizer.load(dataSourcePath);
		int label = faceRecognizer.predict(image);	
	}
	
	/**
	 * 训练
	 * @param trainDirectoryPath 训练的图片所在的文件夹的路径，路径下文件名为“编号-图片编号.扩展名”
	 */
	public void train(String trainDirectoryPath) {
		//筛选出文件夹下的图片文件
		File root = new File(trainDirectoryPath);
		FilenameFilter imageFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				name = name.toLowerCase();
				return name.endsWith(".jpg") || name.endsWith(".png");
			}
		};
		File[] imageFiles = root.listFiles(imageFilter);
        
		//读取图片，分配标签
		MatVector images = new MatVector(imageFiles.length);
		Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
		Buffer buffer = labels.createBuffer();
		IntBuffer intBuffer = (IntBuffer) buffer;
		int count = 0;
		for (File imageFile : imageFiles) {
			Mat image = imread(imageFile.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
			int label = Integer.parseInt(imageFile.getName().split("\\-")[0]);
			images.put(count, image);
			intBuffer.put(count, label);
			count++;
		}
		
		//训练
		FileStorage fileStorage = new FileStorage(TRAIN_RESULT_PATH, CV_STORAGE_FORMAT_XML);
		FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();
		if (fileStorage.isOpened()) {
			faceRecognizer.load(fileStorage);
		}
		faceRecognizer.train(images, labels);
		faceRecognizer.save(TRAIN_RESULT_PATH);
	}

}
