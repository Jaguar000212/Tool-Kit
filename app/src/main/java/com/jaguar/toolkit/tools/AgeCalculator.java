package com.jaguar.toolkit.tools;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.jaguar.toolkit.R;
import com.jaguar.toolkit.databinding.ToolAgeCalculatorBinding;

public class AgeCalculator extends AppCompatActivity {
    ToolAgeCalculatorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ToolAgeCalculatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MaterialToolbar toolbar = binding.incAppbar.materialToolbar;
        toolbar.setLogo(R.mipmap.ic_tool_age);
        toolbar.setTitle("Age Calculator");
        setSupportActionBar(toolbar);
    }
}