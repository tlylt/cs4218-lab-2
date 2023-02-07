package cs4218.stubs;

import cs4218.enums.REPLACE_MODE;
import cs4218.exceptions.RepException;
import cs4218.interfaces.parsers.RepArgsParser;

import java.util.List;

public class RepArgsParserStub implements RepArgsParser {
    private String pattern;
    private String replacement;
    private String filePath;
    private REPLACE_MODE mode;
    private RepException exception;

    public void setValues(String pattern, String replacement, String filePath, REPLACE_MODE mode) {
        this.pattern = pattern;
        this.replacement = replacement;
        this.filePath = filePath;
        this.mode = mode;
    }

    public void setException(RepException ex) {
        this.exception = ex;
    }

    @Override
    public void parseAndValidate(List<String> tokens) throws RepException {
        if (this.exception != null) {
            throw this.exception;
        }
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public String getReplacement() {
        return replacement;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public REPLACE_MODE getMode() {
        return mode;
    }
}
