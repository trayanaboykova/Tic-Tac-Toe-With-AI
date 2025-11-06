package utils;

import org.hyperskill.hstest.exception.outcomes.WrongAnswer;

public enum CellState {

    X("X"), O("O"), EMPTY(" ");

    String value;

    CellState(String value) {
        this.value = value;
    }

    static CellState get(char symbol) {
        switch (symbol) {
            case 'X':
                return X;
            case 'O':
                return O;
            case ' ':
            case '_':
                return EMPTY;
            default:
                throw new WrongAnswer("Bad symbol '" + symbol + "' in the game grid");
        }
    }

    static CellState getOpponent(CellState player) {
        if (player == X) {
            return O;
        } else {
            return X;
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
