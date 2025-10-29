package com.example.businesscontacts_anz.domain.utils


sealed interface DataError: Error {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        NO_INTERNET,
        SERVER_ERROR,
        SERIALIZATION_EXCEPTION,
        UNKNOWN,

        NO_DATA_IN_SERVER_ERROR
    }
    enum class Local: DataError {
        DISK_FULL
    }
}