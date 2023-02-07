package cs4218;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    public String readFileToString(File file) throws IOException {
        return FileUtils.readFileToString(file);
    }
}
