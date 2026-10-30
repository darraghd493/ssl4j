package me.darragh.ssl4j;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents an instruction in SSL.
 *
 * @author darraghd493
 * @since 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum Instruction {
    PUSH_ZERO('a'),
    POP_FIRST_ITEM('b'),
    SUBTRACT_SECOND_ITEM_FROM_FIRST('c'),
    DECREMENT_FIRST_ITEM('d'),
    MODULO_FIRST_ITEM_BY_SECOND('e'),
    PRINT_FIRST_ITEM_AS_CHARACTER('f'),
    ADD_FIRST_AND_SECOND_ITEMS('g'),
    READ_NUMBER_INPUT('h'),
    INCREMENT_FIRST_ITEM('i'),
    READ_CHARACTER_INPUT('j'),
    SKIP_NEXT_IF_ZERO('k'),
    SWAP_FIRST_AND_SECOND_ITEMS('l'),
    MULTIPLY_FIRST_AND_SECOND_ITEMS('m'),
    COMPARE_FIRST_AND_SECOND_ITEMS('n'),
    POP_NTH_ITEM('o'),
    DIVIDE_FIRST_ITEM_BY_SECOND('p'),
    DUPLICATE_FIRST_ITEM('q'),
    PUSH_STACK_LENGTH('r'),
    SWAP_FIRST_AND_NTH_ITEMS('s'),
    JUMP_FORWARD_IF_ZERO('t'),
    JUMP_BACK_IF_NOT_ZERO('u'),
    INCREMENT_FIRST_ITEM_BY_FIVE('v'),
    DECREMENT_FIRST_ITEM_BY_FIVE('w'),
    PRINT_FIRST_ITEM('x'),
    CLEAR_STACK('y'),
    EXIT('z');

    private final char representingCharacter;
}
