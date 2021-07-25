package com.lingman.common.utils


class EventBean {
    var key: String
    var value: Any? = null

    constructor(key: String) {
        this.key = key
    }

    constructor(key: String, value: Any?) {
        this.key = key
        this.value = value
    }
}
