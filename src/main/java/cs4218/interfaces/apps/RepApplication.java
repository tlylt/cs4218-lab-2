package cs4218.interfaces.apps;

import cs4218.exceptions.RepException;

import java.util.List;

public interface RepApplication extends Application {
    void run(List<String> tokens) throws RepException;
}
