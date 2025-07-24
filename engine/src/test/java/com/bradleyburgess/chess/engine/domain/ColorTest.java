package com.bradleyburgess.chess.engine.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorTest {
    @Test
    public void to_string_is_white() {
        Color color = Color.WHITE;
        assertEquals("WHITE", String.valueOf(color));
    }

    @Test
    public void to_string_is_black() {
        Color color = Color.BLACK;
        assertEquals("BLACK", color.toString());
    }
}
