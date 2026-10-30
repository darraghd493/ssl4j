package me.darragh.ssl4j.parser;

import me.darragh.ssl4j.Instruction;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private final Parser parser = new Parser();

    @Test
    void parsesInstructionsInOrderAndIgnoresOtherCharacters() {
        assertEquals(List.of(Instruction.PUSH_ZERO, Instruction.INCREMENT_FIRST_ITEM, Instruction.PRINT_FIRST_ITEM),
                parser.parse("a i!X\nx"));
    }

    @Test
    void commentsEndAtNewline() {
        assertEquals(List.of(Instruction.PUSH_ZERO, Instruction.PRINT_FIRST_ITEM), parser.parse("a#iz\n#abc\nx"));
    }

    @Test
    void emptyAndBlankInputProducesNoInstructions() {
        assertTrue(parser.parse(" \n\t").isEmpty());
    }

    @Test
    void recognizesEveryInstruction() {
        assertEquals(List.of(Instruction.values()), parser.parse("abcdefghijklmnopqrstuvwxyz"));
    }
}
