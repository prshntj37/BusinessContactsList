package com.example.businesscontacts_anz.presentation.mappers

import com.example.businesscontacts_anz.R
import com.example.businesscontacts_anz.domain.utils.DataError

fun DataError.asUiString(): UiString {
    return when(this) {

        DataError.Network.REQUEST_TIMEOUT -> UiString(
            R.string.error_request_timeout
        )

        DataError.Network.NO_INTERNET -> UiString(
            R.string.error_no_internet
        )

        DataError.Network.SERVER_ERROR -> UiString(
            R.string.error_server_error
        )
        DataError.Network.SERIALIZATION_EXCEPTION -> UiString(
            R.string.error_serialization
        )

        DataError.Network.UNKNOWN -> UiString(
            R.string.error_unknown
        )

        DataError.Local.DISK_FULL -> UiString(
            R.string.error_disk_full
        )

        DataError.Network.NO_DATA_IN_SERVER_ERROR -> UiString(
            R.string.no_data_in_server
        )
    }
}