package facerec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

	public static void main(String[] args) {
		APIKeySecret apiKeySecretMap = new APIKeySecret();
		apiKeySecretMap.add("e4e4dae1aada4f3af41cf2c421ae922d", "hMBmkwnJRdDwvbasbkOTXUW59gSr08Xh");
		apiKeySecretMap.add("daeaa22e3e8b6bf688c5216c2cd37e18", "8Hyu6IYQcnxuU63xqMW6elVox3O4lNPP");
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("./key.obj")));
			out.writeObject(apiKeySecretMap);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("./key.obj")));
			apiKeySecretMap = (APIKeySecret) in.readObject();
			in.close();
			System.out.println(apiKeySecretMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
