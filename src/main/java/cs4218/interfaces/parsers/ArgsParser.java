package cs4218.interfaces.parsers;

import cs4218.exceptions.ApplicationException;

import java.util.List;

public interface ArgsParser {
    /**
     * Parse and validate the input. Once validated, any arguments or flags can be retrieved from this object.
     *
     * @throws ApplicationException if an exception occurs.
     */
    void parseAndValidate(List<String> tokens) throws ApplicationException;
}
