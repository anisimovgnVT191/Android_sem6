package com.example.android.lab12

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.textview.MaterialTextView

class PostsAdapter(
    private val sampleData: List<Post>
): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val descriptionView = view.findViewById<MaterialTextView>(R.id.post_description)
        var description: String = ""
            set(value) {
                descriptionView.text = value
                field = value
            }
        private val imageView = view.findViewById<AppCompatImageView>(R.id.post_image)
        var imageUrl: String = ""
            set(value) {
                Glide.with(imageView.context)
                    .load(value)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
                field = value
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.posts_recycler_item, parent, false)
        view.viewTreeObserver.addOnPreDrawListener (
            object : ViewTreeObserver.OnPreDrawListener{
                override fun onPreDraw(): Boolean {
                    val type = viewType
                    val lp = view.layoutParams
                    if (lp is StaggeredGridLayoutManager.LayoutParams) {
                        when (type) {
                            TYPE_FULL -> {
                                lp.isFullSpan = true
                                lp.width = view.width
                                lp.height = (view.height.toFloat() * (3F/4F)).toInt()
                            }
                            TYPE_HALF -> {
                                lp.isFullSpan = false
                                lp.width = view.width
                                lp.height = (view.height.toFloat() * (3F/4F)).toInt()
                            }
                        }
                        view.layoutParams = lp
                        val lm =
                            (parent as RecyclerView).layoutManager as StaggeredGridLayoutManager
                        lm.invalidateSpanAssignments()
                    }
                    view.viewTreeObserver.removeOnPreDrawListener(this)
                    return true
                }
            }
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (imageUrl, description) = sampleData[position]
        holder.imageUrl = imageUrl
        holder.description = description

    }

    override fun getItemCount() = sampleData.size

    override fun getItemViewType(position: Int) =  when(position % 3){
        0 -> TYPE_FULL
        else -> TYPE_HALF
    }

    companion object{
        private const val TYPE_FULL = 0
        private const val TYPE_HALF = 1
    }
}