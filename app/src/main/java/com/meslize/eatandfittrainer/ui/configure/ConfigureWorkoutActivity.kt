package com.meslize.eatandfittrainer.ui.configure

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
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
import kotlinx.android.synthetic.main.adapter_select_exercise_item.view.*

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
private class ExercisesAdapter(items: List<Exercise> = arrayListOf(),
    val checked: SparseBooleanArray = SparseBooleanArray(
        items.size)) : BaseRecyclerViewAdapter<Exercise, RecyclerView.ViewHolder>(
    items) {

  override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
    when (getItemViewType(position)) {
      ViewType.EXERCISE.value -> {
        val holder = viewHolder as ViewHolder
        holder.bind(items[position], position, checked)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
    ViewType.EXERCISE.value -> ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_select_exercise_item, parent, false))

    ViewType.ADD_CUSTOM.value -> ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_add_custom_item, parent, false))
    else -> throw IllegalArgumentException("Unknown view type: $viewType")
  }

  override fun getItemCount(): Int = items.size + 1

  override fun getItemViewType(position: Int): Int {
    return if (position == items.size) {
      ViewType.ADD_CUSTOM.value
    } else {
      ViewType.EXERCISE.value
    }
  }

  //TODO(david) Design a better way to track the checked items
  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(message: Exercise, position: Int, checked: SparseBooleanArray) = with(itemView) {
      name_view.text = message.name

      // We need to set the listener to null so we don't override the real value
      checked_view.setOnCheckedChangeListener(null)
      checked_view.isChecked = checked[position]
      checked_view.setOnCheckedChangeListener({ _, isChecked -> checked.put(position, isChecked) })
    }
  }

  enum class ViewType constructor(val value: Int) {
    EXERCISE(0),
    ADD_CUSTOM(1);
  }
}
