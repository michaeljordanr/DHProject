package br.com.doghero.dhproject.data

import br.com.doghero.dhproject.model.MyHeroesResponse
import com.google.gson.Gson

object MyHeroes {
    fun build(json: String): MyHeroesResponse {
        val gson = Gson()
        return gson.fromJson(json, MyHeroesResponse::class.java)
    }
}
