package com.jaguar.toolkit.tools

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jaguar.toolkit.R
import com.jaguar.toolkit.databinding.ToolAverageCalculatorBinding
import kotlin.math.pow

class AverageCalculator : AppCompatActivity() {
    private lateinit var binding: ToolAverageCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ToolAverageCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.incAppbar.materialToolbar.apply {
            title = "Average Calculator"
            setLogo(R.mipmap.ic_tool_average)
        })

        binding.calculate.setOnClickListener {
            val result = binding.outputText
            try {
                val numbers = getNumbers()
                val sum = calculateSum(numbers)
                val average = calculateAverage(numbers)
                val harmonicMean = harmonicMean(numbers)
                val geometricMean = geometricMean(numbers)
                val count = numbers.size
                val largest = numbers.maxOrNull() ?: 0.0
                val smallest = numbers.minOrNull() ?: 0.0
                result.setText(
                    """
                    Result: $average
                    Geometric Mean: $geometricMean
                    Harmonic Mean: $harmonicMean
                    Sum: $sum
                    Count: $count
                    Largest: $largest
                    Smallest: $smallest
                """.trimIndent()
                )
            } catch (e: NumberFormatException) {
                result.setText("Error: Format is not correct. Separate numbers with commas.")
            }
        }

        binding.clear.setOnClickListener {
            binding.inputText.text.clear()
            binding.outputText.text.clear()
        }

        binding.copyClp.setOnClickListener {
            val text = "Input: ${binding.inputText.text}\n${binding.outputText.text}"
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("Copied Text", text))
            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }

        binding.share.setOnClickListener {
            val text = "Input: ${binding.inputText.text}\n${binding.outputText.text}"
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendIntent, null))
        }
    }

    @Throws(NumberFormatException::class)
    private fun getNumbers(): DoubleArray {
        return binding.inputText.text.toString().split(",").map { it.toDouble() }.toDoubleArray()
    }

    private fun calculateSum(numbers: DoubleArray): Double {
        return numbers.sum()
    }

    private fun harmonicMean(numbers: DoubleArray): Double {
        return numbers.size / numbers.sumOf { 1 / it }
    }

    private fun geometricMean(numbers: DoubleArray): Double {
        return numbers.fold(1.0) { acc, d -> acc * d }.pow(1.0 / numbers.size)
    }

    private fun calculateAverage(numbers: DoubleArray): Double {
        return numbers.average()
    }
}