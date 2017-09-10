package com.meslize.eatandfittrainer.ui.start

import android.content.Intent
import android.os.Bundle
import com.meslize.eatandfittrainer.R
import com.meslize.eatandfittrainer.ui.BaseActivity
import com.meslize.eatandfittrainer.ui.configure.ConfigureWorkoutActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_start)

    start.setOnClickListener { startActivity(Intent(this, ConfigureWorkoutActivity::class.java)) }
  }
}
