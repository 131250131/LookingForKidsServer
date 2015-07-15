package face;

import java.io.File;
import java.io.FilenameFilter;

import java.nio.Buffer;
import java.nio.IntBuffer;

import static org.bytedeco.javacpp.opencv_contrib.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.*;

public class FaceRecognition {

	private static final String TRAIN_RESULT_PATH = "./res/trained.xml";
	
	/**
	 * ʶ��һ��ͼƬ�е���
	 * @param testImagePath Ҫʶ���ͼƬ��·��
	 * @param dataSourcePath ����Դ��·��
	 */
	public void recognize(String testImagePath, String dataSourcePath) {
		Mat image = imread(testImagePath, CV_LOAD_IMAGE_GRAYSCALE);
		FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();
		faceRecognizer.load(dataSourcePath);
		int[] labels = new int[5];
		double[] confidence = new double[5];
		faceRecognizer.predict(image, labels, confidence);
		MappingTable mappingTable = new MappingTable();
		try {
			mappingTable.load("./res/mapping.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {
			if (labels[i] != 0) {
				System.out.println(mappingTable.getValue(labels[i])+confidence[i]);
			}
		}
	}
	
	/**
	 * ѵ��
	 * @param trainDirectoryPath ѵ����ͼƬ���ڵ��ļ��е�·����·�����ļ���Ϊ�����-ͼƬ���.��չ����
	 */
	public void train(String trainDirectoryPath) {
		//ɸѡ���ļ����µ�ͼƬ�ļ�
		File root = new File(trainDirectoryPath);
		FilenameFilter imageFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				name = name.toLowerCase();
				return name.endsWith(".jpg") || name.endsWith(".png");
			}
		};
		File[] imageFiles = root.listFiles(imageFilter);
        
		//��ȡͼƬ�������ǩ
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
		//ѵ��
		FileStorage fileStorage = new FileStorage(TRAIN_RESULT_PATH, CV_STORAGE_FORMAT_XML);
		FaceRecognizer faceRecognizer = createLBPHFaceRecognizer(1, 8, 8, 8, 0.2);
		if (fileStorage.isOpened()) {
			faceRecognizer.load(fileStorage);
			faceRecognizer.update(images, labels);
		} else {
			faceRecognizer.train(images, labels);
		}
		faceRecognizer.save(TRAIN_RESULT_PATH);
	}

}
