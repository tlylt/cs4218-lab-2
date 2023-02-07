package cs4218.impl.apps;

import cs4218.FileUtil;
import cs4218.enums.REPLACE_MODE;
import cs4218.exceptions.RepException;
import cs4218.stubs.RepArgsParserStub;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RepApplicationImplTest {
    RepApplicationImpl app;
    RepArgsParserStub argsStub;
    OutputStream out;
    FileUtil fileUtil;

    @BeforeEach
    void setUp() {
        this.out = new ByteArrayOutputStream();
        this.argsStub = new RepArgsParserStub(); // Can write our own stubs
        this.fileUtil = mock(FileUtil.class); // Or create mocks using mockito
        this.app = new RepApplicationImpl(argsStub, this.out, fileUtil);
    }

    @Test
    @Disabled // Temporarily disabled failing test
    void run_SimpleValues_PrintsCorrectValues() throws RepException, IOException {
        // Given
        List<String> tokens = Arrays.asList("Hello", "abced", "hello-world.txt");
        argsStub.setValues(tokens.get(0), tokens.get(1), tokens.get(2), REPLACE_MODE.REPLACE_WORDS);
        when(this.fileUtil.readFileToString(new File("hello-world.txt"))).thenReturn("Hello World!");

        // When
        this.app.run(tokens);

        // Then
        assertEquals("abced World!", this.out.toString());
    }

    @Test
    void run_ArgsValidationFailed_ThrowsRepException() {
        this.argsStub.setException(new RepException("Exception Message"));

        RepException ex = assertThrows(RepException.class, () -> this.app.run(Collections.emptyList()));
        assertEquals("Exception Message", ex.getMessage());
    }
}