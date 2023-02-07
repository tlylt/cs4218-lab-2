package cs4218.impl.apps;

import cs4218.FileUtil;
import cs4218.exceptions.RepException;
import cs4218.interfaces.apps.RepApplication;
import cs4218.interfaces.parsers.RepArgsParser;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class RepApplicationImpl implements RepApplication {
    final RepArgsParser args;
    final OutputStream out;
    final FileUtil fileUtil;

    public RepApplicationImpl(RepArgsParser args, OutputStream out, FileUtil fileUtil) {
        this.args = args;
        this.out = out;
        this.fileUtil = fileUtil;
    }

    @Override
    public void run(List<String> tokens) throws RepException {
        this.args.parseAndValidate(tokens);

        String filePath = this.args.getFilePath();
        String fileContents = this.getFileContents(filePath);
        String pattern = this.args.getPattern();
        String replacement = this.args.getReplacement();

        String result;

        switch (this.args.getMode()) {
            case REPLACE_WORDS:
                result = this.replaceWords(pattern, replacement, fileContents);
                break;
            case REPLACE_CHARACTERS:
                result = this.replaceCharacters(pattern, replacement, fileContents);
                break;
            default:
                throw new RepException("Invalid Mode");
        }

        try {
            IOUtils.write(result + System.lineSeparator(), this.out);
        } catch (IOException e) {
            throw new RepException("Could not write to output stream", e);
        }
    }

    private String replaceWords(String pattern, String replacement, String fileContents) {
        return fileContents.replace(pattern, replacement);
    }

    private String replaceCharacters(String pattern, String replacement, String fileContents) {
        String charSetRegex = String.format("[%s]", pattern);
        return fileContents.replaceAll(charSetRegex, replacement);
    }

    private String getFileContents(String filePath) throws RepException {
        try {
            File file = new File(filePath);
            return this.fileUtil.readFileToString(file);
        } catch (IOException e) {
            throw new RepException("Invalid file path provided", e);
        }
    }
}
