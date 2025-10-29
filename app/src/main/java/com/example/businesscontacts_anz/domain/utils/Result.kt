package com.example.businesscontacts_anz.domain.utils


sealed interface Result<out D, out E: Error> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: com.example.businesscontacts_anz.domain.utils.Error>(val error: E): Result<Nothing, E>
}