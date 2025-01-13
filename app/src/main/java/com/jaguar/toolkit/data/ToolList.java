package com.jaguar.toolkit.data;

import com.jaguar.toolkit.R;
import com.jaguar.toolkit.objects.Tool;
import com.jaguar.toolkit.tools.AgeCalculator;
import com.jaguar.toolkit.tools.AverageCalculator;
import com.jaguar.toolkit.tools.Counter;
import com.jaguar.toolkit.tools.LoanCalculator;

import java.util.Arrays;
import java.util.List;

public class ToolList {
    final static private List<Tool> tools = Arrays.asList(
            new Tool("Average Calculator", "Calculate average with some other interesting results.", R.mipmap.ic_tool_average, AverageCalculator.class),
            new Tool("Interest Calculator", "Calculates both simple and compound interests.", R.mipmap.ic_tool_loan, LoanCalculator.class),
            new Tool("Counter", "A simple counter to increment, decrement and reset.", R.mipmap.ic_tool_counter, Counter.class),
            new Tool("Age Calculator", "Calculates the age between two dates.", R.mipmap.ic_tool_age, AgeCalculator.class)
            );

    public static List<Tool> getTools() {
        return tools;
    }
}
