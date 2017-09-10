package com.meslize.eatandfittrainer.ui.configure

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.meslize.eatandfittrainer.R
import com.meslize.eatandfittrainer.database.entity.Exercise
import com.meslize.eatandfittrainer.di.Provide
import com.meslize.eatandfittrainer.ui.BaseActivity
import com.meslize.eatandfittrainer.ui.workout.WorkoutActivity
import kotlinx.android.synthetic.main.activity_configure_workout.*

class ConfigureWorkoutActivity : BaseActivity() {
  private lateinit var viewModel: ConfigureWorkoutViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_configure_workout)

    viewModel = Provide.configureWorkoutViewModel(this)
    lifecycle.addObserver(viewModel)

    viewModel.exercises.observe(this, Observer {
      it?.let { items -> exercises_view.adapter = ExercisesAdapter(items) }
    })

    viewModel.loadWorkouts()

    start.setOnClickListener { startActivity(Intent(this, WorkoutActivity::class.java)) }
  }
}

//TODO(david) Add section headers, icons...
private class ExercisesAdapter(val items: List<Exercise>) : BaseAdapter() {

  override fun getView(position: Int, view: View?, parent: ViewGroup): View {
    val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_checked, parent,
        false) as TextView
    view.text = items[position].name

    return view
  }

  override fun getItem(position: Int) = items[position]

  override fun getItemId(position: Int) = position.toLong()

  override fun getCount() = items.size
}
