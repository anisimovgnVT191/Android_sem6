package com.example.android.lab12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.textview.MaterialTextView

class HeaderAdapter(
    private val headerText: String
): RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val headerView= view.findViewById<MaterialTextView>(R.id.header_text)
        var headerText: String = ""
            set(value) {
                headerView.text = value
                field = value
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_layout, parent, false)
        view.viewTreeObserver.addOnPreDrawListener (
            object : ViewTreeObserver.OnPreDrawListener{
                override fun onPreDraw(): Boolean {
                    val lp = view.layoutParams
                    if (lp is StaggeredGridLayoutManager.LayoutParams) {
                        lp.isFullSpan = true
                        val lm =
                            (parent as RecyclerView).layoutManager as StaggeredGridLayoutManager
                        lm.invalidateSpanAssignments()
                    }
                    view.viewTreeObserver.removeOnPreDrawListener(this)
                    return true
                }
            }
        )
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.headerText = headerText
    }

    override fun getItemCount() = 1

}