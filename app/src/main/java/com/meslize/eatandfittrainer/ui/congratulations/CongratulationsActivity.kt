package com.meslize.eatandfittrainer.ui.congratulations

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import com.meslize.eatandfittrainer.R
import kotlinx.android.synthetic.main.activity_congratulations.*
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size


class CongratulationsActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_congratulations)
    startConfettiAnimation()
  }

  override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onPostCreate(savedInstanceState, persistentState)
  }

  private fun startConfettiAnimation() {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)

    val x = displayMetrics.widthPixels / 2.toFloat()
    val y = (-50).toFloat()
    confetti_view.build()
        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
        .setDirection(0.0, 359.0)
        .setSpeed(2f, 6f)
        .setFadeOutEnabled(true)
        .setTimeToLive(1500L)
        .addShapes(Shape.RECT, Shape.CIRCLE)
        .addSizes(Size(12))
        .setPosition(x, y)
        .stream(300, 5000L)
  }
}
