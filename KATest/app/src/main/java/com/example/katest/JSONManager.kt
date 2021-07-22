package com.example.katest

import android.os.AsyncTask
import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

@RequiresApi(Build.VERSION_CODES.N)
class JSONManager : AsyncTask<Void, Void, Void>() {

    private var url: String = "http://storage42.com/modulotest/data.json"
    var data: String? = ""
    var organisedDeviceData: String? = ""
    var organisedUserData: String? = ""
    private lateinit var dm: DataManager
    private lateinit var um: UserDataManager

    override fun doInBackground(vararg p0: Void?): Void? {
        try {
            var objectURL : URL = URL(url)
            var http : HttpURLConnection = objectURL.openConnection() as HttpURLConnection
            var inputStream : InputStream = http.inputStream
            var buffer : BufferedReader = inputStream.bufferedReader()
            var currentLine : String? = ""

            while(currentLine != null) {
                currentLine = buffer.readLine()
                data += currentLine
            }

            dm = DataManager()
            parseData()

            organisedDeviceData = dm.displayData()
            organisedUserData = um.displayData()
        } catch(e : MalformedURLException) {
            e.printStackTrace()
        }

        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        forwardData()
    }

    private fun forwardData() {
        if(organisedDeviceData != null) {
            FirstFragment.saveData(organisedDeviceData)
        }
        if(organisedUserData != null) {
            SecondFragment.saveData(organisedUserData)
        }
    }

    private fun parseData() {
        var jsonParseData : JSONObject = JSONObject(data)

        var jsonParseDataDevices : JSONArray = jsonParseData.getJSONArray("devices")
        for(i in 0 until jsonParseDataDevices.length()) {
            var jsonElement : JSONObject = jsonParseDataDevices.getJSONObject(i)
            var id = jsonElement.getString("id")
            var deviceName = jsonElement.getString("deviceName")
            var intensity = jsonElement.optString("intensity")
            var position = jsonElement.optString("position")
            var temperature = jsonElement.optString("temperature")
            var mode = jsonElement.optString("mode")
            var productType = jsonElement.getString("productType")
            var tmpDeviceDataElement: DeviceData = DeviceData(id,deviceName,intensity,position,temperature,mode,productType)
            dm.addElement(tmpDeviceDataElement)
        }

        var jsonParseDataUser : JSONObject = jsonParseData.getJSONObject("user")
        um = UserDataManager(
            jsonParseDataUser.optString("firstName"),
            jsonParseDataUser.optString("lastName"),
            jsonParseDataUser.optJSONObject("address").optString("city"),
            jsonParseDataUser.optJSONObject("address").optString("postalCode"),
            jsonParseDataUser.optJSONObject("address").optString("street"),
            jsonParseDataUser.optJSONObject("address").optString("streetCode"),
            jsonParseDataUser.optJSONObject("address").optString("country"),
            jsonParseDataUser.optString("birthDate")
        )
    }
}