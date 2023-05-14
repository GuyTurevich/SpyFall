package com.example.newspyfall
import android.content.Context
import android.content.Intent
import org.json.JSONArray
import org.json.JSONObject

class GameActivity (private val userList: List<User>,private val context: Context) {

    private val locationsList: MutableList<String> = loadLocationsFromJson(context)
    private var location: String = ""

    fun startGame() {

        while(true) {
            this.location = locationsList.random()
            val spy = userList.random()
            spy.isSpy = true
            for (user in userList) {
                wordPrompt(user)
            }

            spy.isSpy = false
            locationsList.remove(location)
        }
    }

    private fun wordPrompt(user : User) {

        val locationOrSpy = if (!user.isSpy) location else "מרגל"
        val intent = Intent(context, WordPromptActivity::class.java)
        intent.putExtra("userName", user.name)
        intent.putExtra("location", locationOrSpy)
        context.startActivity(intent)
    }

    private fun loadLocationsFromJson(context: Context): MutableList<String> {

        val locationsList = mutableListOf<String>()

        try {
            val inputStream = context.assets.open("locations.json")
            val json = inputStream.bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(json)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val location = jsonObject.getString("location")
                locationsList.add(location)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return locationsList
    }

}