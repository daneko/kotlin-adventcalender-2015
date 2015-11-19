package com.github.daneko.android.retrolambda;

import android.view.View;

import android.support.design.widget.Snackbar;

/**
 *
 */
public class Extensions {
    public static class ViewExtensions {
        public static Snackbar snack(View view, String text) {
            return Snackbar.make(view, text, Snackbar.LENGTH_LONG).setAction("Action", null);
        }
    }
}
