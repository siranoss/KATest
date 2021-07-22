package com.example.katest

class ViewModel {
    private var data: String? = null

    @JvmName("setData1")
    public fun setData(data: String?) {
        this.data = data
    }

    @JvmName("getData1")
    public fun getData(): String? {
        return data
    }
}