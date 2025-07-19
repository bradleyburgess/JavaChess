package com.bradleyburgess.chess.engine.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorTest {
    @Test
    public void to_string_is_white() {
        Color color = Color.WHITE;
        Assertions.assertEquals("WHITE", String.valueOf(color));
    }

    @Test
    public void to_string_is_black() {
        Color color = Color.BLACK;
        Assertions.assertEquals("BLACK", color.toString());
    }
}
