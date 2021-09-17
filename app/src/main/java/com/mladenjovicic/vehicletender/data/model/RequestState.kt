package com.mladenjovicic.vehicletender.data.model

class RequestState(val pending: Boolean, val successful: Boolean, val errorMessage: String) {
    companion object {
        fun failed(errorMessage: String?) = RequestState(
            pending = false,
            successful = false,
            errorMessage = errorMessage ?: ""
        )
        val pending = RequestState(pending = true, successful = false, errorMessage = "")
        val success = RequestState(pending = false, successful = true, errorMessage = "")
        val failed = RequestState(pending = false, successful = false, errorMessage = "")
    }
}