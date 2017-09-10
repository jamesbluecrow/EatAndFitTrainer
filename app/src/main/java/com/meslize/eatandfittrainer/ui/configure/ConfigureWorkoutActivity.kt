package com.meslize.eatandfittrainer.ui.configure

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meslize.eatandfittrainer.R
import com.meslize.eatandfittrainer.database.entity.Exercise
import com.meslize.eatandfittrainer.di.Provide
import com.meslize.eatandfittrainer.ui.BaseActivity
import com.meslize.eatandfittrainer.ui.common.BaseRecyclerViewAdapter
import com.meslize.eatandfittrainer.ui.workout.WorkoutActivity
import kotlinx.android.synthetic.main.activity_configure_workout.*
import kotlinx.android.synthetic.main.layout_configure_workout_item.view.*

class ConfigureWorkoutActivity : BaseActivity() {
  private lateinit var viewModel: ConfigureWorkoutViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_configure_workout)
    exercises_view.layoutManager = LinearLayoutManager(this)

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
private class ExercisesAdapter(items: List<Exercise>) : BaseRecyclerViewAdapter<Exercise, ExercisesAdapter.ViewHolder>(
    items) {

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(items[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_configure_workout_item, parent, false)
    return ViewHolder(view)
  }


  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(message: Exercise) = with(itemView) {
      name_view.text = message.name
    }
  }
}
