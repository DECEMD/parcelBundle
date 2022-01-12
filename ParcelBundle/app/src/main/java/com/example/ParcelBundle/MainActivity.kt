package com.example.ParcelBundle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.example.ParcelBundle.databinding.ActivityMainBinding
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var state: State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.countB.setOnClickListener { counterPlus() }
        binding.colorB.setOnClickListener { changeColor() }
        binding.visibilityB.setOnClickListener { visibilitySet() }

        state = savedInstanceState?.getParcelable(STATE) ?: State(
            counterValue = 0,
            changingColor = (R.color.black),
            visibilitySetting = true
        )
        dataChangedState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(STATE, state)

    }


    private fun counterPlus()=with(binding){
        state.counterValue++
        dataChangedState()
    }


    private fun changeColor(){
        state.changingColor =
            Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
        dataChangedState()
    }


    private fun visibilitySet() {
        state.visibilitySetting = !state.visibilitySetting
        dataChangedState()
    }


    private fun dataChangedState() = with(binding){
        tvText.text = state.counterValue.toString()
        tvText.setTextColor(state.changingColor)
        tvText.visibility = if(state.visibilitySetting) VISIBLE else INVISIBLE
    }

    @Parcelize
   class State(
       var counterValue: Int,
       var changingColor: Int,
       var visibilitySetting: Boolean
   ) : Parcelable

    companion object{
        @JvmStatic private val STATE = "STATE"
    }

}


