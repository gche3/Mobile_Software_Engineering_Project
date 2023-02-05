//Grace He, 2/5/2023
package com.example.mobilesoftwareengineeringproject.network

import android.util.Log
import com.example.mobilesoftwareengineeringproject.models.Item
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.net.URL
import java.util.*

private val TAG = Client::class.java.name

object Client {

    fun getData() : List<Item> {
        //create list to store items in
        val itemList = mutableListOf<Item>()
        //get data through url
        val data = URL("https://fetch-hiring.s3.amazonaws.com/hiring.json").readText()

        //read the data line by line via scanner, attempt to create an object with each line, and
        //  add those to the list.
        val scanner = Scanner(data)
        while (scanner.hasNextLine()) {
            val line: String = scanner.nextLine()
            //empty names and improperly formatted segments do not get added to the list.
            try {
                val i: Item = jacksonObjectMapper().readValue(line, Item::class.java)
                require(i.name.isNotEmpty())
                itemList.add(i)
            } catch (e: Exception) {
                Log.e(TAG, "Caught an exception.")
                continue
            }
        }
        scanner.close()

        //puts all of the items in order in terms of id number
        itemList.sort()
        return itemList
    }

    fun separateData(list: List<Item>) : List<List<Item>> {
        //separate lists to store every individual list in, plus returnList which will eventually be
        //  bigList in the ListActivity file.
        val returnList = mutableListOf<List<Item>>()
        val list1 = mutableListOf<Item>()
        val list2 = mutableListOf<Item>()
        val list3 = mutableListOf<Item>()
        val list4 = mutableListOf<Item>()

        //each item gets added to a list based on its list id
        for (item in list) {
            when (item.listId) {
                1 -> list1.add(item)
                2 -> list2.add(item)
                3 -> list3.add(item)
                else -> list4.add(item)
            }
        }

        //those lists then all get added to the big list
        returnList.add(list)
        returnList.add(list1)
        returnList.add(list2)
        returnList.add(list3)
        returnList.add(list4)

        return returnList
    }
}