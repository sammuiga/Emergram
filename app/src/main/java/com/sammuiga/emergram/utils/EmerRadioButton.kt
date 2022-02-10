package com.sammuiga.emergram.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton


class EmerRadioButton(context: Context, attributeSet: AttributeSet) :
    AppCompatRadioButton(context, attributeSet) {

    init {
        applyFont()
    }

    private fun applyFont() {

        val typeface: Typeface =
            Typeface.createFromAsset(context.assets, "Montserrat-Bold.ttf")
        setTypeface(typeface)
    }
}