package com.meslize.eatandfittrainer.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel

open class BaseViewModel : ViewModel(){
  var initialized = false

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  private fun onCreate() {
    initialized = true
  }
}
