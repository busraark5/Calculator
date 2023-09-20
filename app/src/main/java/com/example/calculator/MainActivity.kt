package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private var inputText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberButtons = arrayOf(
            binding.button0,
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9
        )

        for (button in numberButtons) {
            button.setOnClickListener {
                onNumberClick(button.text.toString())
            }
        }

        binding.buttonPlus.setOnClickListener {
            onOperatorClick("+")
        }

        binding.buttonEqual.setOnClickListener {
            onEqualClick()
        }

        binding.buttonAc.setOnClickListener {
            onClearClick()
        }
    }

    private fun onNumberClick(number: String) {
        inputText += number
        updateResult()
    }

    private fun onOperatorClick(op: String) {
        if (inputText.isNotEmpty()) {
            if (inputText.last().isDigit()) {
                inputText += op
                updateResult()
            }
        }
    }

    private fun onEqualClick() {
        try {
            val result = result(inputText)
            inputText = result.toString()
            updateResult()
        } catch (e: Exception) {
            inputText = ""
            updateResult()
        }
    }

    private fun onClearClick() {
        inputText = ""
        updateResult()
    }

    private fun updateResult() {
        binding.textViewResult.text = inputText
    }

    private fun result(input: String): Double {
        return when {
            input.isEmpty() -> 0.0
            input.contains("+") -> {
                val parts = input.split("+")
                parts.map { it.trim().toDouble() }.sum()
            }
            else -> 0.0
        }
    }

}