package com.jaguar.toolkit.data

import com.jaguar.toolkit.R
import com.jaguar.toolkit.objects.Tool
import com.jaguar.toolkit.tools.AgeCalculator
import com.jaguar.toolkit.tools.AverageCalculator
import com.jaguar.toolkit.tools.Counter
import com.jaguar.toolkit.tools.LoanCalculator

object ToolList {
    private val tools = listOf(
        Tool(
            "Average Calculator",
            "Calculate average with some other interesting results.",
            R.mipmap.ic_tool_average,
            AverageCalculator::class.java
        ), Tool(
            "Loan Calculator",
            "Calculates EMI on the basis of loan amount, interest rate and tenure.",
            R.mipmap.ic_tool_loan,
            LoanCalculator::class.java
        ), Tool(
            "Counter",
            "A simple counter to increment, decrement and reset.",
            R.mipmap.ic_tool_counter,
            Counter::class.java
        ), Tool(
            "Age Calculator",
            "Calculates the age between two dates.",
            R.mipmap.ic_tool_age,
            AgeCalculator::class.java
        )
    )

    fun getTools(): List<Tool<*>> = tools
}