package com.jaguar.toolkit.tools;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.jaguar.toolkit.R;
import com.jaguar.toolkit.databinding.ToolCounterBinding;

public class Counter extends AppCompatActivity {

    ToolCounterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ToolCounterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MaterialToolbar toolbar = binding.incAppbar.materialToolbar;
        toolbar.setLogo(R.mipmap.ic_tool_counter);
        toolbar.setTitle("Counter");
        setSupportActionBar(toolbar);

        Button btnIncrement = binding.incrementBtn;
        Button btnDecrement = binding.decrementBtn;
        Button btnReset = binding.resetBtn;
        TextView counterText = binding.counterText;

        counterText.setText("0");

        btnIncrement.setOnClickListener(v -> {
            int counter = Integer.parseInt(counterText.getText().toString());
            counter++;
            counterText.setText(String.valueOf(counter));
        });

        btnDecrement.setOnClickListener(v -> {
            int counter = Integer.parseInt(counterText.getText().toString());
            counter--;
            counterText.setText(String.valueOf(counter));
        });

        btnReset.setOnClickListener(v -> {
            counterText.setText("0");
        });
    }
}