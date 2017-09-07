package com.meslize.eatandfittrainer.database.entity.mock

import com.meslize.eatandfittrainer.database.entity.Equipment
import com.meslize.eatandfittrainer.database.entity.Exercise
import com.meslize.eatandfittrainer.database.entity.Workout

class Database {
  fun create(): List<Workout> {

    val equipment = Equipment(id = "1", name = "Equipment 1", description = "Equipment 1")

    val exercise = Exercise(id = "1", name = "Exercise 1", description = "Exercise 1",
        equipments = arrayListOf(equipment, equipment, equipment), set = 3, repetitions = 15)

    val workout = Workout(id = "1", name = "Day of week", description = "Workout of the day",
        exercises = arrayListOf(exercise, exercise, exercise))

    return arrayListOf(workout, workout, workout)
  }
}
