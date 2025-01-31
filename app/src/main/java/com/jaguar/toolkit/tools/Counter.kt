package com.jaguar.toolkit.tools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jaguar.toolkit.R
import com.jaguar.toolkit.databinding.ToolCounterBinding

class Counter : AppCompatActivity() {
    private lateinit var binding: ToolCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ToolCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.incAppbar.materialToolbar.apply {
            setLogo(R.mipmap.ic_tool_counter)
            title = "Counter"
        })

        binding.counterText.text = "0"

        binding.incrementBtn.setOnClickListener {
            val counter = binding.counterText.text.toString().toInt() + 1
            binding.counterText.text = counter.toString()
        }

        binding.decrementBtn.setOnClickListener {
            val counter = binding.counterText.text.toString().toInt() - 1
            binding.counterText.text = counter.toString()
        }

        binding.resetBtn.setOnClickListener {
            binding.counterText.text = "0"
        }
    }
}