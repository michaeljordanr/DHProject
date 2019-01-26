package br.com.doghero.dhproject.model

import com.google.gson.annotations.SerializedName

data class Hero(
        var id: Int,
        @SerializedName("is_superhero")
        var isSuperhero: Boolean,
        var user: User,
        @SerializedName("address_neighborhood")
        var addressNeighborhood: String,
        var price: Double
)