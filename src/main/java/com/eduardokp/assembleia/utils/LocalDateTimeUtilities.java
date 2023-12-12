package com.eduardokp.assembleia.utils;

import java.time.LocalDateTime;

/**
 * Classe de utilizadas para a classe @LocalDateTime.
 */
public class LocalDateTimeUtilities {

    public static boolean isDataMaior(LocalDateTime data, LocalDateTime data2) {
        return data.isBefore(data2);
    }
}
