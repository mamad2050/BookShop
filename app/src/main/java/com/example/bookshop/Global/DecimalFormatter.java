package com.example.bookshop.Global;

import java.text.DecimalFormat;

public class DecimalFormatter {

    public static String formatted(int number){

        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(number);

    }
}
