package com.ironhack.WondercodersCRMDataLayer.classes;

import com.ironhack.WondercodersCRMDataLayer.Enums.Color;

public class TextColor {

    public static void changeTo(Color newColor) {
        switch(newColor) {
            case WHITE_BRIGHT -> System.out.print("\033[0;97m");
            case WHITE_BOLD_BRIGHT -> System.out.print("\033[1;97m");
            case PURPLE -> System.out.print("\033[0;35m");
            case BLUE -> System.out.print("\033[0;34m");
            case RED -> System.out.print("\033[0;31m");
            case GREEN -> System.out.print("\033[0;32m");
        }
    }

    public static void reset() {
        System.out.print("\033[0m");
    }

}
