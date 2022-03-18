package com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.android.catsapp.R
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapter
import com.example.android.catsapp.uilayer.catslistfeature.delegateadapter.DelegateAdapterItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.CharacteristicItem
import com.example.android.catsapp.uilayer.catslistfeature.fragments.breedsdetails.recycler.models.Characteristics
import com.google.android.material.textview.MaterialTextView

class CharacteristicAdapter :
    DelegateAdapter<CharacteristicItem, CharacteristicAdapter.CharacteristicViewHolder>(
        CharacteristicItem::class.java
    ) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_details_temperament, parent, false)

        return CharacteristicViewHolder(view)
    }

    override fun bindViewHolder(
        model: CharacteristicItem,
        viewHolder: CharacteristicViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    override fun onViewRecycled(viewHolder: CharacteristicViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.unBind()
    }

    inner class CharacteristicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val temperamentName: MaterialTextView = itemView.findViewById(R.id.temperament_name)
        private val rateHolder: LinearLayoutCompat = itemView.findViewById(R.id.star_rate_holder)

        fun bind(item: CharacteristicItem) {
            bindTemperamentName(item.characteristics)
            bindRate(item.value)
        }

        fun bindTemperamentName(char: Characteristics) {
            temperamentName.text = with(itemView.context) {
                when (char) {
                    Characteristics.AffectionLevel -> getString(R.string.affection_level)
                    Characteristics.Adaptability -> getString(R.string.adaptability)
                    Characteristics.ChildFriendly -> getString(R.string.child_friendly)
                    Characteristics.DogFriendly -> getString(R.string.dog_friendly)
                    Characteristics.EnergyLevel -> getString(R.string.energy_level)
                    Characteristics.Grooming -> getString(R.string.grooming)
                    Characteristics.HealthIssues -> getString(R.string.health_issues)
                    Characteristics.Intelligence -> getString(R.string.intelligence)
                    Characteristics.SheddingLevel -> getString(R.string.shedding_level)
                    Characteristics.SocialNeeds -> getString(R.string.social_needs)
                    Characteristics.StrangerFriendly -> getString(R.string.stranger_friendly)
                    Characteristics.Vocalisation -> getString(R.string.vocalisation)
                }
            }
        }

        fun bindRate(value: Int) {
            for (i in 0 until value) {
                val star = rateHolder.getChildAt(i) as AppCompatImageView
                star.setImageResource(R.drawable.ic_baseline_star_24)
            }
        }

        fun unBind() {
            rateHolder.children.toList().forEach { star ->
                (star as AppCompatImageView).setImageResource(R.drawable.ic_baseline_star_outline_24)
            }
        }
    }
}