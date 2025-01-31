package com.jaguar.toolkit.tools

import android.app.DatePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jaguar.toolkit.R
import com.jaguar.toolkit.databinding.ToolAgeCalculatorBinding
import java.util.Calendar

class AgeCalculator : AppCompatActivity() {
    private lateinit var binding: ToolAgeCalculatorBinding

    private fun showDatePickerDialog(textView: TextView) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this, { _: DatePicker?, year: Int, month: Int, day: Int ->
                textView.text = "$day/${month + 1}/$year"
            }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]
        ).show()
    }

    private fun calculateAge(dateText1: TextView, dateText2: TextView): String {
        val (day1, month1, year1) = dateText1.text.split("/").map { it.toInt() }
        val (day2, month2, year2) = dateText2.text.split("/").map { it.toInt() }

        var deltaYear = year1 - year2
        var deltaMonth = month1 - month2
        var deltaDay = day1 - day2

        if (deltaDay < 0) {
            deltaMonth--
            deltaDay += 30
        }
        if (deltaMonth < 0) {
            deltaYear--
            deltaMonth += 12
        }

        return "$deltaYear years, $deltaMonth months, $deltaDay days"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ToolAgeCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.incAppbar.materialToolbar.apply {
            setLogo(R.mipmap.ic_tool_age)
            title = "Age Calculator"
        })

        binding.pickDate1.setOnClickListener { showDatePickerDialog(binding.dateText1) }
        binding.pickDate2.setOnClickListener { showDatePickerDialog(binding.dateText2) }

        binding.calcAge.setOnClickListener {
            try {
                binding.ageResult.text =
                    "Age: ${calculateAge(binding.dateText1, binding.dateText2)}"
            } catch (e: NumberFormatException) {
                binding.ageResult.text = "Error: Format is not correct. Select dates properly."
            }
        }

        binding.btnClip.setOnClickListener {
            val age = binding.ageResult.text.toString()
            if (age != "Age: ") {
                val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(ClipData.newPlainText("Age", age))
                Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
            }
        }
    }
}