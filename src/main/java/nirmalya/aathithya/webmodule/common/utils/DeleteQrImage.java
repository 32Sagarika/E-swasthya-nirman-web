package nirmalya.aathithya.webmodule.common.utils;

import java.io.File;

public class DeleteQrImage {
	
	public static boolean getDeleteImage(String path) {

		boolean deleted = false;
		try {
			File file2 = new File(path);
			Thread.sleep(10000);
			deleted = file2.delete();
		   System.out.println("####"+deleted);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return deleted;
	}

}
