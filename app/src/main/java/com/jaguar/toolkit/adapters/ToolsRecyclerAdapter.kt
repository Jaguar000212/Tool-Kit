package com.jaguar.toolkit.adapters

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jaguar.toolkit.R
import com.jaguar.toolkit.components.ToolCard
import com.jaguar.toolkit.objects.Tool

class ToolsRecyclerAdapter(context: Context?, private val toolsList: List<Tool<*>>) :
    RecyclerView.Adapter<ToolsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val toolCard = ToolCard(parent.context).build()
        return ViewHolder(toolCard)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tool = toolsList[position]
        holder.bind(tool)
    }

    override fun getItemCount(): Int = toolsList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val toolName: TextView = itemView.findViewById(R.id.tool_name)
        private val toolBtn: ImageButton = itemView.findViewById(R.id.tool_btn)
        private var tool: Tool<*>? = null

        init {
            toolBtn.setOnClickListener {
                tool?.let {
                    val intent = Intent(itemView.context, it.getActivity())
                    itemView.context.startActivity(intent)
                }
            }
            toolBtn.setOnLongClickListener {
                tool?.let {
                    Toast.makeText(itemView.context, it.getDescription(), Toast.LENGTH_SHORT).show()
                }
                true
            }
        }

        fun bind(tool: Tool<*>) {
            this.tool = tool
            toolName.text = tool.getName()
            toolBtn.setImageResource(tool.getIcon())
        }
    }
}