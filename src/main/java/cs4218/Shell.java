package cs4218;

import cs4218.interfaces.apps.Application;
import cs4218.impl.apps.RepApplicationImpl;
import cs4218.exceptions.ApplicationException;
import cs4218.impl.parsers.RepArgsParserImpl;
import cs4218.interfaces.parsers.RepArgsParser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Shell {
    private final InputStream in;
    private final PrintStream out;
    private final PrintStream err;

    public Shell(InputStream in, PrintStream out, PrintStream err) {
        this.in = in;
        this.out = out;
        this.err = err;
    }

    public void start() {
        try (Scanner scanner = new Scanner(this.in)) {
            while (true) {
                this.out.print("> ");

                if (!scanner.hasNextLine()) {
                    break;
                }

                String line = scanner.nextLine();
                List<String> tokens = Arrays.asList(line.split(" "));
                String command = tokens.get(0);

                runApplication(command, tokens);

                this.out.println();
            }
        }
    }

    private void runApplication(String command, List<String> tokens) {
        Application app;

        if ("rep".equals(command)) {
            RepArgsParser args = new RepArgsParserImpl();
            app = new RepApplicationImpl(args, this.out, new FileUtil());
        } else {
            this.err.println("Command not found");
            return;
        }

        try {
            app.run(tokens);
        } catch (ApplicationException e) {
            this.err.println(e.formatException());
        }
    }
}
