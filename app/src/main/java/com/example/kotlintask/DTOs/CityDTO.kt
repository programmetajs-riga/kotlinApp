package com.example.kotlintask.DTOs

import org.json.JSONArray
import org.json.JSONException

class CityDTO {
    var cityId: String? = null
    var city: String? = null

    @Throws(JSONException::class)
    fun getValue(content: String?): ArrayList<CityDTO>? {
        val city = ArrayList<CityDTO>()
        val jsonArray = JSONArray(content)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val cityDTO = CityDTO()
            cityDTO.cityId = jsonObject.getString("id")
            cityDTO.city = jsonObject.getString("name")
            city.add(cityDTO)
        }
        return city
    }
}