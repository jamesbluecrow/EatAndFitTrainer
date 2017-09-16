package com.meslize.eatandfittrainer.di

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.meslize.eatandfittrainer.ui.configure.ConfigureWorkoutViewModel


class Provide {
  companion object {
    fun configureWorkoutViewModel(activity: FragmentActivity): ConfigureWorkoutViewModel =
        ViewModelProviders.of(activity).get(ConfigureWorkoutViewModel::class.java)
  }
}
