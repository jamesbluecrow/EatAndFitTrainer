package com.meslize.eatandfittrainer.ui.configure

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import com.meslize.eatandfittrainer.database.entity.Exercise
import com.meslize.eatandfittrainer.database.entity.mock.Database
import com.meslize.eatandfittrainer.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ConfigureWorkoutViewModel : BaseViewModel(), LifecycleObserver {
  private val subscriptions = CompositeDisposable()
  val exercises = MutableLiveData<List<Exercise>>()

  fun loadWorkouts() {
    if (!initialized) { // Not sure what is called first, this method or onCreate in the parent
      subscriptions.add(Database.listOfExercises().subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe { items ->
            exercises.value = items
          })
    }
  }

  override fun onCleared() {
    subscriptions.clear()
    super.onCleared()
  }
}
