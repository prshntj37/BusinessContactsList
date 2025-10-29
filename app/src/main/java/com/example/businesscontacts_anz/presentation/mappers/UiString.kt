package com.example.businesscontacts_anz.presentation.mappers

import android.content.Context
import androidx.annotation.StringRes

class UiString(
    @StringRes val id: Int,
) {
    fun asString(context: Context): String {
        return  context.getString(id)
    }
}