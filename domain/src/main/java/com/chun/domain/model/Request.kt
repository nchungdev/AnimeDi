package com.chun.domain.model

data class Request(val path: String = "", val query: Map<String, String> = emptyMap()) {
    companion object {
        val DEFAULT_REQUEST = Request()
    }
}