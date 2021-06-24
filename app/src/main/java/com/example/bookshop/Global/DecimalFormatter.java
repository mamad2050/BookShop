package com.example.bookshop.Global;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;

import java.text.DecimalFormat;

public class DecimalFormatter {

    public static String formatted(int number){

        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(number);

    }

}
