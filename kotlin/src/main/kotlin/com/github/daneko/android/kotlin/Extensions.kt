package com.github.daneko.android.kotlin

import android.support.design.widget.*
import android.view.*

/**
 *
 */
fun View.snack(text: String): Snackbar =
        Snackbar.make(this, text, Snackbar.LENGTH_LONG).
                setAction("Action", null)
