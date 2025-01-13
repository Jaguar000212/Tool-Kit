package com.jaguar.toolkit.tools;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.jaguar.toolkit.R;
import com.jaguar.toolkit.databinding.ToolAgeCalculatorBinding;

import java.util.Calendar;

public class AgeCalculator extends AppCompatActivity {
    ToolAgeCalculatorBinding binding;

    private void showDatePickerDialog(TextView textView) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            textView.setText(date);
        }, year, month, day);
        datePickerDialog.show();
    }

    protected String calculateAge(TextView dateText1, TextView dateText2) {
        String date1 = dateText1.getText().toString();
        String date2 = dateText2.getText().toString();
        String[] date1Parts = date1.split("/");
        String[] date2Parts = date2.split("/");
        int day1 = Integer.parseInt(date1Parts[0]);
        int month1 = Integer.parseInt(date1Parts[1]);
        int year1 = Integer.parseInt(date1Parts[2]);
        int day2 = Integer.parseInt(date2Parts[0]);
        int month2 = Integer.parseInt(date2Parts[1]);
        int year2 = Integer.parseInt(date2Parts[2]);

        int deltaYear = year1 - year2;
        int deltaMonth = month1 - month2;
        int deltaDay = day1 - day2;

        if (deltaDay < 0) {
            deltaMonth--;
            deltaDay += 30;
        }
        if (deltaMonth < 0) {
            deltaYear--;
            deltaMonth += 12;
        }

        return deltaYear + " years, " + deltaMonth + " months, " + deltaDay + " days";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ToolAgeCalculatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MaterialToolbar toolbar = binding.incAppbar.materialToolbar;
        toolbar.setLogo(R.mipmap.ic_tool_age);
        toolbar.setTitle("Age Calculator");
        setSupportActionBar(toolbar);

        Button pickDate = binding.pickDate1;
        pickDate.setOnClickListener(v -> {
            showDatePickerDialog(binding.dateText1);
        });

        Button pickDate2 = binding.pickDate2;
        pickDate2.setOnClickListener(v -> {
            showDatePickerDialog(binding.dateText2);
        });

        Button calculate = binding.calcAge;
        calculate.setOnClickListener(v -> {
            try {
                String age = calculateAge(binding.dateText1, binding.dateText2);
                binding.ageResult.setText("Age: " + age);
            } catch (NumberFormatException e) {
                binding.ageResult.setText("Error: Format is not correct. Select dates properly.");
            }
        });

        Button copy = binding.btnClip;
        copy.setOnClickListener(v -> {
            String age = binding.ageResult.getText().toString();
            if (!age.equals("Age: ")) {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Age", age);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }
}