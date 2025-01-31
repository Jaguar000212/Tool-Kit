package com.jaguar.toolkit.tools

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jaguar.toolkit.R
import com.jaguar.toolkit.databinding.ToolLoanCalculatorBinding
import kotlin.math.pow

class LoanCalculator : AppCompatActivity() {
    private lateinit var binding: ToolLoanCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ToolLoanCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.incAppbar.materialToolbar.apply {
            title = "Loan Calculator"
            setLogo(R.mipmap.ic_tool_loan)
        })

        binding.calcBtn.setOnClickListener {
            val principalText = binding.principalText.text.toString()
            val rateText = binding.rateText.text.toString()
            val timeText = binding.timeText.text.toString()

            if (principalText.isEmpty() || rateText.isEmpty() || timeText.isEmpty()) {
                binding.resultText.setText("Required fields are empty")
                return@setOnClickListener
            }
            try {
                val loanAmount = principalText.toDouble()
                val interestRate = rateText.toDouble()
                val loanTerm = timeText.toDouble()

                val emi = calculateInstallment(loanAmount, interestRate, loanTerm)
                val result = getResult(emi, loanTerm, loanAmount)

                binding.resultText.setText(result)
            } catch (e: NumberFormatException) {
                binding.resultText.setText("Invalid input")
            }
        }

        binding.clearBtn.setOnClickListener {
            binding.principalText.text.clear()
            binding.rateText.text.clear()
            binding.timeText.text.clear()
            binding.resultText.text.clear()
        }

        binding.copyBtn.setOnClickListener {
            val result = """
                Principal Amount: ${binding.principalText.text}
                Rate of Interest: ${binding.rateText.text}%
                Loan Term: ${binding.timeText.text} Years
                ${binding.resultText.text}
            """.trimIndent()
            if (binding.resultText.text.isNotEmpty()) {
                val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(ClipData.newPlainText("Loan Calculator", result))
                Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
            }
        }

        binding.shareBtn.setOnClickListener {
            val result = """
                Principal Amount: ${binding.principalText.text}
                Rate of Interest: ${binding.rateText.text}%
                Loan Term: ${binding.timeText.text} Years
                ${binding.resultText.text}
            """.trimIndent()
            if (binding.resultText.text.isNotEmpty()) {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, result)
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(sendIntent, null))
            }
        }
    }

    private fun calculateInstallment(
        loanAmount: Double, interestRate: Double, loanTerm: Double
    ): Double {
        val monthlyInterestRate = interestRate / 100 / 12
        val tenure = loanTerm * 12
        return loanAmount * monthlyInterestRate * (1 + monthlyInterestRate).pow(tenure) / ((1 + monthlyInterestRate).pow(
            tenure
        ) - 1)
    }

    companion object {
        private fun getResult(emi: Double, loanTerm: Double, loanAmount: Double): String {
            val totalAmount = emi * loanTerm * 12
            val totalInterest = totalAmount - loanAmount
            val interestPercentage = (totalInterest / totalAmount) * 100

            return String.format(
                "Monthly Installment: %.2f\nTotal Amount Repayable: %.2f\nTotal Interest Repayable: %.2f\nInterest Percentage: %.2f%%",
                emi,
                totalAmount,
                totalInterest,
                interestPercentage
            )
        }
    }
}