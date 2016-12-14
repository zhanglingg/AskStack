package com.askcicle.library.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;

/**
 *  Created by lin on 2016-12-08.
 */
public class StringStyleUtils {
    public static SpannableString format(Context context, String text, int style) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new TextAppearanceSpan(context, style), 0, text.length(),
                0);
        return spannableString;
    }
}
