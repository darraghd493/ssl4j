package me.darragh.ssl4j.interpreter;

import lombok.RequiredArgsConstructor;
import me.darragh.ssl4j.Instruction;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Stack;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@RequiredArgsConstructor
public final class Interpreter {
    private final @NotNull InterpreterConfiguration configuration;

    public Interpreter() {
        this(InterpreterConfiguration.DEFAULT);
    }

    public void interpret(@NotNull List<Instruction> instructions) {
        Stack<Integer> stack = new Stack<>();

        int currentInstruction = 0;
        boolean awaitingSkip = false;

        while (currentInstruction < instructions.size()) {
            if (awaitingSkip) {
                awaitingSkip = false;
                currentInstruction++;
                continue;
            }

            Instruction instruction = instructions.get(currentInstruction);

            switch (instruction) {
                case PUSH_ZERO -> stack.push(0);
                case POP_FIRST_ITEM -> {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                }
                case SUBTRACT_SECOND_ITEM_FROM_FIRST -> {
                    if (stack.size() >= 2) {
                        int first = stack.pop(), second = stack.pop();
                        stack.push(first - second);
                    }
                }
                case DECREMENT_FIRST_ITEM -> {
                    if (!stack.isEmpty()) {
                        stack.push(stack.pop() - 1);
                    }
                }
                case MODULO_FIRST_ITEM_BY_SECOND -> {
                    if (stack.size() >= 2) {
                        int first = stack.pop(), second = stack.pop();
                        if (second != 0) {
                            stack.push(first % second);
                        }
                    }
                }
                case PRINT_FIRST_ITEM_AS_CHARACTER -> {
                    if (!stack.isEmpty()) {
                        this.configuration.outputProvider().printCharacterOutput((char) (int) stack.peek());
                    }
                }
                case ADD_FIRST_AND_SECOND_ITEMS -> {
                    if (stack.size() >= 2) {
                        int first = stack.pop(), second = stack.pop();
                        stack.push(first + second);
                    }
                }
                case READ_NUMBER_INPUT -> {
                    stack.push(this.configuration.inputProvider().readNumberInput());
                }
                case INCREMENT_FIRST_ITEM -> {
                    if (!stack.isEmpty()) {
                        stack.push(stack.pop() + 1);
                    }
                }
                case READ_CHARACTER_INPUT -> {
                    stack.push((int) this.configuration.inputProvider().readCharacterInput());
                }
                case SKIP_NEXT_IF_ZERO -> {
                    if (!stack.isEmpty() && stack.peek() == 0) {
                        awaitingSkip = true;
                    }
                }
                case SWAP_FIRST_AND_SECOND_ITEMS -> {
                    if (stack.size() >= 2) {
                        int first = stack.pop(), second = stack.pop();
                        stack.push(first);
                        stack.push(second);
                    }
                }
                case MULTIPLY_FIRST_AND_SECOND_ITEMS -> {
                    if (stack.size() >= 2) {
                        int first = stack.pop(), second = stack.pop();
                        stack.push(first * second);
                    }
                }
                case COMPARE_FIRST_AND_SECOND_ITEMS -> {
                    if (stack.size() >= 2) {
                        int first = stack.pop(), second = stack.pop();
                        stack.push(first == second ? 1 : 0);
                    }
                }
                case POP_NTH_ITEM -> {
                    if (!stack.isEmpty()) {
                        int n = stack.peek();
                        int targetIdx = stack.size() - 1 - n;
                        if (targetIdx >= 0 && targetIdx < stack.size()) {
                            stack.remove(targetIdx);
                        }
                    }
                }
                case DIVIDE_FIRST_ITEM_BY_SECOND -> {
                    if (stack.size() >= 2) {
                        int first = stack.pop(), second = stack.pop();
                        if (second != 0) {
                            stack.push(first / second);
                        }
                    }
                }
                case DUPLICATE_FIRST_ITEM -> {
                    if (!stack.isEmpty()) {
                        stack.push(stack.peek());
                    }
                }
                case PUSH_STACK_LENGTH -> stack.push(stack.size());
                case SWAP_FIRST_AND_NTH_ITEMS -> {
                    if (!stack.isEmpty()) {
                        int n = stack.peek();
                        int targetIdx = stack.size() - 1 - n;
                        int topIdx = stack.size() - 1;
                        if (targetIdx >= 0 && targetIdx < stack.size()) {
                            int temp = stack.get(targetIdx);
                            stack.set(targetIdx, stack.get(topIdx));
                            stack.set(topIdx, temp);
                        }
                    }
                }
                case JUMP_FORWARD_IF_ZERO -> {
                    if (!stack.isEmpty() && stack.peek() == 0) {
                        int opened = 0;
                        currentInstruction++;
                        while (currentInstruction < instructions.size()) {
                            Instruction current = instructions.get(currentInstruction);
                            if (current == Instruction.JUMP_BACK_IF_NOT_ZERO && opened == 0) {
                                break;
                            } else if (current == Instruction.JUMP_FORWARD_IF_ZERO) {
                                opened++;
                            } else if (current == Instruction.JUMP_BACK_IF_NOT_ZERO) {
                                opened--;
                            }
                            currentInstruction++;
                        }
                    }
                }
                case JUMP_BACK_IF_NOT_ZERO -> {
                    if (!stack.isEmpty() && stack.peek() != 0) {
                        int closed = 0;
                        currentInstruction--;
                        while (currentInstruction >= 0) {
                            Instruction current = instructions.get(currentInstruction);
                            if (current == Instruction.JUMP_FORWARD_IF_ZERO && closed == 0) {
                                break;
                            } else if (current == Instruction.JUMP_BACK_IF_NOT_ZERO) {
                                closed++;
                            } else if (current == Instruction.JUMP_FORWARD_IF_ZERO) {
                                closed--;
                            }
                            currentInstruction--;
                        }
                    }
                }
                case INCREMENT_FIRST_ITEM_BY_FIVE -> {
                    if (!stack.isEmpty()) {
                        stack.push(stack.pop() + 5);
                    }
                }
                case DECREMENT_FIRST_ITEM_BY_FIVE -> {
                    if (!stack.isEmpty()) {
                        stack.push(stack.pop() - 5);
                    }
                }
                case PRINT_FIRST_ITEM -> {
                    if (!stack.isEmpty()) {
                        this.configuration.outputProvider().printNumberOutput(stack.peek());
                    }
                }
                case CLEAR_STACK -> stack.clear();
                case EXIT -> System.exit(0);
            }

            currentInstruction++;
        }
    }
}