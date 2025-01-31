package com.jaguar.toolkit.components

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.jaguar.toolkit.databinding.CardToolBinding

class ToolCard(context: Context) {
    private val binding = CardToolBinding.inflate(LayoutInflater.from(context))
    private val card = CardView(context).apply {
        radius = 20f
        cardElevation = 10f
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(20, 20, 20, 20)
        }
        addView(binding.root)
    }
    private var description: String? = null

    fun setName(name: String?) = apply {
        binding.toolName.text = name
    }

    fun setDescription(description: String?) = apply {
        this.description = description
    }

    fun setIcon(iconID: Int) = apply {
        binding.toolBtn.setImageResource(iconID)
    }

    fun build(): CardView = card
}