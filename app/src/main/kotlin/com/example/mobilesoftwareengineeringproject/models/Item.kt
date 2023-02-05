//Grace He, 2/5/2023
package com.example.mobilesoftwareengineeringproject.models

class Item (private val id: Int, val listId: Int, val name: String) : Comparable<Item> {
    //compareTo function based on id number
    override fun compareTo(other: Item): Int {
        return if (id > other.id) {
            1
        } else if (id < other.id) {
            -1
        } else {
            0
        }
    }
    //toString function that was mostly just for use during testing
    override fun toString(): String {
        return "{id: $id, listId: $listId, name: $name}"
    }
}