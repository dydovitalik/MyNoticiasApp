package com.example.misnoticiasapp.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Source {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}