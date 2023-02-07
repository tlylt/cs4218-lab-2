package cs4218.impl.parsers;

import cs4218.enums.REPLACE_MODE;
import cs4218.exceptions.RepException;
import cs4218.interfaces.parsers.RepArgsParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepArgsParserImpl implements RepArgsParser {
    private static final String CHARACTER_FLAG = "-c";
    private final Set<String> flags = new HashSet<>();
    private final List<String> args = new ArrayList<>();

    @Override
    public void parseAndValidate(List<String> tokens) throws RepException {
        tokens.forEach(this::parseToken);
        this.validate();
    }

    private void parseToken(String token) {
        token = token.trim();

        if (token.isEmpty()) {
            return;
        }

        if (token.startsWith("-")) {
            this.flags.add(token);
        } else {
            this.args.add(token);
        }
    }

    private void validate() throws RepException {
        boolean notEnoughArguments = args.size() < 3;
        if (notEnoughArguments) {
            throw new RepException("Not Enough Arguments");
        }

        boolean invalidFlags = !flags.isEmpty() && !flags.contains(CHARACTER_FLAG);
        if (invalidFlags) {
            throw new RepException("Invalid Flags");
        }
    }

    @Override
    public String getPattern() {
        return args.get(1);
    }

    @Override
    public String getReplacement() {
        return args.get(2);
    }

    @Override
    public String getFilePath() {
        return args.get(3);
    }

    @Override
    public REPLACE_MODE getMode() {
        if (this.flags.isEmpty()) {
            return REPLACE_MODE.REPLACE_WORDS;
        } else {
            return REPLACE_MODE.REPLACE_CHARACTERS;
        }
    }
}
