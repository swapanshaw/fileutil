package org.opensource.fileutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.PriorityQueue;

import org.opensource.fileutil.enums.FileTypeEnum;
import org.opensource.fileutil.tri.Trie;

/**
 * FileUtils help to do different operations on the different type of file Like Search, sort on the content of
 * the file
 * <p>
 * 
 * @author sshaw
 *
 */
public class FileUtils {

    private static Trie trie = new Trie();
    
	public Map<String, Integer> topKfrequentString(String filePath, Integer k) {
		String ext = checkValidFileExtension(filePath);
        try {
            readFile(filePath, ext);
            return findTopKText(filePath);
        } catch (NumberFormatException | IOException e) {
            throw new RuntimeException(e);
        }
		
	}
    private Map<String, Integer> findTopKText(String filePath) throws IOException {
    	
    	String ext = checkValidFileExtension(filePath);
    	readTextFile(filePath, ext);
    	//trie.inse
    	return null;
	}
	/**
     * This containsString method help to find a particular text in the file exist or not, if exists it return
     * true, else false.
     * 
     * @param file
     *            path of the file
     * @param text
     *            is the string which need to be searched in the file
     * @return
     */
    public static boolean containsString(final String filePath, final String text) {
        String ext = checkValidFileExtension(filePath);
        try {
            readFile(filePath, ext);
            return findText(text);
        } catch (NumberFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * To check file provided is supported by the FileUtils library
     * 
     * @param filePath
     *            is path of file
     */
    private static String checkValidFileExtension(final String filePath) {
        checkNotNull(filePath);
        final String str[] = filePath.split("\\.");
        final String ext = str.length == 1 ? "txt" : str[1];
        boolean isValidExt = false;
        for (FileTypeEnum fileTypeEnum : FileTypeEnum.values()) {
            if (fileTypeEnum.getValue().equalsIgnoreCase(ext)) {
                isValidExt = true;
                break;
            }
        }

        if (isValidExt) {
            return ext;
        } else {
            throw new IllegalArgumentException("Not a valid file extesion supported by FileUtils api");
        }
    }

    private static <T> void checkNotNull(final T arg) {
        if (null == arg) {
            throw new NullPointerException("args should not be null");
        }
    }

    private static Trie readFile(final String file, final String ext) throws IOException {
        trie = new Trie();
        switch (ext) {
            case "txt":
                readTextFile(file, ext);
            case "csv":
                readTextFile(file, ext);
            case "xml":
                readXmlFile(file, ext);
            case "docx":
                readDocxFile(file, ext);
        }

        return trie;
    }

    private static void readDocxFile(String file, String ext) {
        // TODO Auto-generated method stub

    }

    private static void readXmlFile(String file, String ext) {

    }

    private static void readTextFile(String file, String ext) throws IOException {
        for (String line : Files.readAllLines(Paths.get(file))) {
            for (String part : line.split("\\s+")) {
                trie.insert(part);
            }
        }
    }

    private static boolean findText(final String text) {
        return trie.find(text);
    }

}
