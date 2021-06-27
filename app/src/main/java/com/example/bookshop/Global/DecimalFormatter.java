package com.example.bookshop.Global;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;

import java.text.DecimalFormat;

public class DecimalFormatter {

    public static String convert(int number){

        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(number);

    }

    public static String convert2(int number){

        DecimalFormat decimalFormat = new DecimalFormat("####,####");
        return decimalFormat.format(number);

    }


}
