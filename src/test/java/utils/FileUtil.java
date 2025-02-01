package utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class FileUtil {

  public static String read(String path) throws IOException {
    File file = new File(path);
    return FileUtils.readFileToString(file, "UTF-8");
  }
}
