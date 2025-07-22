package com.bradleyburgess.chess.engine.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

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
