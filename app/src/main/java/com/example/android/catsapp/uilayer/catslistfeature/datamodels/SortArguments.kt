package com.example.android.catsapp.uilayer.catslistfeature.datamodels

import kotlin.reflect.KProperty1

enum class SortArguments: SortArgument {
    LifeSpan {
        override val sortBy: KProperty1<Breed, String>
            get() = Breed::lifeSpan
    },
    Weight {
        override val sortBy: KProperty1<Breed, String>
            get() = Breed::weight
    }
}

interface SortArgument {
    val sortBy: KProperty1<Breed, String>
}