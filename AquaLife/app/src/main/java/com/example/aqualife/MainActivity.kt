package com.example.aqualife

import android.os.Bundle
//import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.aqualife.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bdCalcular.setOnClickListener { this.checkWeightAge()  }
        binding.icRefresh.setOnClickListener { this.clearInputs() }
    }

    private fun clearInputs() {
        binding.editIdade.text = null
        binding.editPeso.text = null
    }

    private fun getAgePercent(age: Int): Double {
        val percent = when (age) {
            in 1..20 -> 1.0
            in 21..40 -> 1.1
            in 61..80 -> 1.3
            in 41..60 -> 1.2
            in 81..200 -> 1.4
            else -> 1.0
        }
        return percent
    }

    private fun checkWeightAge() {
        val age = binding.editIdade.text.toString()
        val weight = binding.editPeso.text.toString()

        val agePercent = getAgePercent(Integer.parseInt(age))
        val amountWaterToDrink = (Integer.parseInt(weight) * 35 * agePercent).roundToInt()
        binding.txtResultadoMl.text = "$amountWaterToDrink ml"
        //Log.i("peso", weight.toString())
    }

}