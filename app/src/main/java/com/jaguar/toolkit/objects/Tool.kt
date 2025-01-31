package com.jaguar.toolkit.objects

import androidx.appcompat.app.AppCompatActivity

data class Tool<T : AppCompatActivity>(
    private val name: String,
    private val description: String,
    private val icon: Int,
    private val activity: Class<T>
) {
    fun getName() = name
    fun getDescription() = description
    fun getIcon() = icon
    fun getActivity() = activity
}