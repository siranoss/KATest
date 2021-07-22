package com.example.katest

import org.json.JSONArray
import org.json.JSONObject

class DataManager {
    var dataList: ArrayList<DeviceData> = arrayListOf<DeviceData>()

    public fun addElement(device: DeviceData) {
        dataList.add(device)
    }

    public fun displayData(): String {
        var ret = ""
        for(e in dataList) {
            var singleDevice : String = "----------" + "\n" + "id: " + e.id + "\n" +
                                        "deviceName: " + e.deviceName + "\n"
            if(e.intensity != null && e.intensity != "") {
                singleDevice += "intensity: " + e.intensity + "\n"
            }
            if(e.position != null && e.position != "") {
                singleDevice += "position: " + e.position + "\n"
            }
            if(e.temperature != null && e.temperature != "") {
                singleDevice += "temperature: " + e.temperature + "\n"
            }
            if(e.mode != null && e.mode != "") {
                singleDevice += "mode: " + e.mode + "\n"
            }
            singleDevice += "productType: " + e.productType + "\n"

            ret += singleDevice
        }
        return ret
    }
}