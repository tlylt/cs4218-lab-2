package cs4218.interfaces.apps;

import cs4218.exceptions.ApplicationException;

import java.util.List;

public interface Application {
    /**
     * Runs the application.
     *
     * @throws ApplicationException if an exception occurs while the application is running.
     */
    void run(List<String> tokens) throws ApplicationException;
}
