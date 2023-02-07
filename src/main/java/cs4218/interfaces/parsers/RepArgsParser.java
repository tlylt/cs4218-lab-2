package cs4218.interfaces.parsers;

import cs4218.enums.REPLACE_MODE;
import cs4218.exceptions.RepException;

import java.util.List;

public interface RepArgsParser extends ArgsParser {
    void parseAndValidate(List<String> tokens) throws RepException;

    String getPattern();

    String getReplacement();

    String getFilePath();

    REPLACE_MODE getMode();
}
