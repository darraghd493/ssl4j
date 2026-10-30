package me.darragh.ssl4j.parser;

import me.darragh.ssl4j.Instruction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public final class Parser {
    // TODO: Configuration?

    public @NotNull List<Instruction> parse(@NotNull String code) {
        String[] lines = code.split("\n");
        List<Instruction> instructions = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank()) continue;
            for (char c : line.toCharArray()) {
                if (c == '#') {
                    break;
                }

                for (Instruction instruction : Instruction.values()) {
                    if (instruction.getRepresentingCharacter() == c) {
                        instructions.add(instruction);
                        break;
                    }
                }
            }
        }
        return instructions;
    }
}
