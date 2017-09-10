package com.meslize.eatandfittrainer.ui.workout

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.meslize.eatandfittrainer.R
import com.meslize.eatandfittrainer.ui.congratulations.CongratulationsActivity
import kotlinx.android.synthetic.main.activity_workout.*

class WorkoutActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_workout)

    stop.setOnClickListener { startActivity(Intent(this, CongratulationsActivity::class.java)) }
  }
}
