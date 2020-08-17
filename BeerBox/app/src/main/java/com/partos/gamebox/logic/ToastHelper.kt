package com.partos.gamebox.logic

import android.content.Context
import android.widget.Toast
import com.partos.gamebox.R

class ToastHelper() {

    fun toastNoDices(context: Context) {
        Toast.makeText(context, context.getText(R.string.toast_no_dices), Toast.LENGTH_SHORT).show()
    }

    fun toastTooFewDices(context: Context) {
        Toast.makeText(context, context.getText(R.string.toast_too_few_dices), Toast.LENGTH_SHORT)
            .show()
    }

    fun toastTooMuchDices(context: Context) {
        Toast.makeText(context, context.getText(R.string.toast_too_much_dices), Toast.LENGTH_SHORT)
            .show()
    }

    fun toastSuccessfullySaved(context: Context) {
        Toast.makeText(
            context,
            context.getText(R.string.toast_successfully_saved),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun toastNoNameGiven(context: Context) {
        Toast.makeText(
            context,
            context.getText(R.string.toast_no_cauldron_name_given),
            Toast.LENGTH_SHORT
        )
            .show()
    }

    fun toastNoAlcohols(context: Context) {
        Toast.makeText(context, context.getText(R.string.toast_no_alcohols), Toast.LENGTH_SHORT)
            .show()
    }
}