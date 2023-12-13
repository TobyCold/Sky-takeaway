package com.sky.util;


import java.util.function.Function;
import java.util.function.Supplier;

public interface HandleNull {

    void handleNull(Runnable t, Supplier f);
}
