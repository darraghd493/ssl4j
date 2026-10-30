package me.darragh.ssl4j.interpreter;

import me.darragh.ssl4j.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {
    private String run(String program, int number, char character) {
        StringBuilder output = new StringBuilder();
        var input = new InterpreterConfiguration.InputProvider() {
            public int readNumberInput() {
                return number;
            }
            public char readCharacterInput() {
                return character;
            }
        };
        var sink = new InterpreterConfiguration.OutputProvider() {
            public void printNumberOutput(int value) {
                output.append(value).append(',');
            }
            public void printCharacterOutput(char value) {
                output.append(value);
            }
        };
        new Interpreter(new InterpreterConfiguration(input, sink)).interpret(new Parser().parse(program));
        return output.toString();
    }

    @Test void arithmeticAndStackOperations() {
        assertEquals("10,10,", run("avqgxavlx", 0, ' '));
        assertEquals("-5,", run("ava c x", 0, ' '));
    }

    @Test void inputAndOutputPreserveTypes() {
        assertEquals("42,A", run("hxjf", 42, 'A'));
    }

    @Test void skipsAndLoops() {
        assertEquals("0,", run("akix", 0, ' '));
        assertEquals("3,2,1,0,", run("htxdux", 3, ' '));
        assertEquals("0,", run("atativiudux", 0, ' '));
    }

    @Test void emptyStackOperationsAreIgnoredAndClearRemovesValues() {
        assertEquals("0,", run("bcdefgiklmnpqsuvwxayrx", 0, ' '));
    }
}
