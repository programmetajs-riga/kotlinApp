package com.example.kotlintask.DTOs

import org.json.JSONArray
import org.json.JSONException

class LocationDTO {
    var Id: String? = null
    var Name: String? = null
    var Location: String? = null
    companion object {
        @Throws(JSONException::class)
        fun GetValue(content: String?): ArrayList<LocationDTO>? {
            val location = ArrayList<LocationDTO>()
            val jsonArray =
                JSONArray("[{\"id\":1,\"name\":\"AmericannnnnnnnnnnnnnnnFootball\"},{\"id\":2,\"name\":\"Basketball\"},{\"id\":3,\"name\":\"Cricket\"},{\"id\":4,\"name\":\"MixedMartialArts\"},{\"id\":5,\"name\":\"RugbyLeague\"},{\"id\":6,\"name\":\"Nodetails\"}]")
            for (i in 0 until jsonArray.length()) {
                val jsonParsing = LocationDTO()
                val jsonObject = jsonArray.getJSONObject(i)
                jsonParsing.Id = jsonObject.getString("id")
                jsonParsing.Name = jsonObject.getString("name")
                //  jsonParsing.Location = jsonObject.getString("address");
                location.add(jsonParsing)
            }
            return location
        }
    }
}