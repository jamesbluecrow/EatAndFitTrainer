package com.meslize.eatandfittrainer.di

import com.meslize.eatandfittrainer.ui.workouts.WorkoutsListViewModel

class Provide {
  companion object {
    fun workoutsListViewModel() = WorkoutsListViewModel()
  }
}