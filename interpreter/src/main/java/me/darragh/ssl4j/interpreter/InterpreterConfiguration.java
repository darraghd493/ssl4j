package me.darragh.ssl4j.interpreter;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * @see Interpreter
 * @author darraghd493
 * @since 1.0.0
 */
public record InterpreterConfiguration(
        @NotNull InputProvider inputProvider,
        @NotNull OutputProvider outputProvider
) {
    public static final InterpreterConfiguration DEFAULT = new InterpreterConfiguration(InputProvider.DEFAULT, OutputProvider.DEFAULT);

    public interface InputProvider {
        InputProvider DEFAULT = new InputProvider() {
            private final Scanner scanner = new Scanner(System.in);

            @Override
            public int readNumberInput() {
                return scanner.nextInt();
            }

            @Override
            public char readCharacterInput() {
                return scanner.next().charAt(0);
            }
        };

        int readNumberInput();

        char readCharacterInput();
    }

    public interface OutputProvider {
        OutputProvider DEFAULT = new OutputProvider() {
            @Override
            public void printNumberOutput(int number) {
                System.out.print(number);
            }

            @Override
            public void printCharacterOutput(char character) {
                System.out.print(character);
            }
        };

        void printNumberOutput(int number);

        void printCharacterOutput(char character);
    }
}
