package com.meslize.eatandfittrainer.database.entity

class Exercise(val id: String, val name: String, val description: String, val equipments: List<Equipment>, val set: Int,
    val repetitions: Int)
//TODO(david) Add Time and Photo