package br.com.doghero.dhproject.model

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("first_name")
        var firstName: String,
        @SerializedName("image_url")
        var imageUrl: String
)