package vn.nopcommerce.util;

import vn.nopcommerce.setting.Setting;;;

public class CommonUtils {
	/**
	 * <p><b>Main method (Using to test)</b></p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(configPath("/abc/def/"));
		System.out.println(configPath("\\abc\\def/"));
	}

	/**
	 * <p><b>Config path</b></p>
	 * If path begin with "/" or "\\" then delete them<br>
	 * If path contains "\\" then replace to "/"<br>
	 * <br>
	 * @param path
	 * @return String path
	 */
	public static String configPath(String path) {
		
		if (path != null && (path.startsWith("/") || path.startsWith("\\"))) {
			path = path.substring(1);
		}

		return path.replace("\\", "/");
	}

	/**
	 * <p><b>Check exists file name</b></p>
	 * 
	 * If exists file name then return true else return false<br><br>
	 * 
	 * @param path
	 * @return path
	 */
	public static boolean isExistsFileName(String relativePath) throws Exception {

		relativePath = configPath(relativePath);	
		String excelFileName = getFileName(relativePath);
		
		return excelFileName.contains(".");
	}
	
	/**
	 * <p><b>Get full path</b></p>
	 * 
	 * @param path
	 * @return fullPath
	 */
	public static String getFullPath(String path) {

		StringBuilder sb = new StringBuilder();
		
		sb.append(Setting.TEST_DATA_PATH)
			.append(configPath(path));
		
		return sb.toString();
	}

	/**
	 * <p><b>Get extension name</b></p>
	 * 
	 * @param path
	 * @return extensionName
	 */
	public static String getExtensionName(String path) {
		return path.split("\\.")[1];
	}

	/**
	 * <p><b>Get file name</b></p>
	 * 
	 * @param path
	 * @return fileName
	 */
	public static String getFileName(String path) {
		String[] arrPath = path.split("/");
		
		if (arrPath.length == 1) {
			return arrPath[0];
		} else {
			return arrPath[arrPath.length - 1];
		}
	}
}
