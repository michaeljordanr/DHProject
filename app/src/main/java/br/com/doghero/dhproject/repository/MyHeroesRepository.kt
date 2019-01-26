package br.com.doghero.dhproject.repository

import br.com.doghero.dhproject.data.MyHeroes
import br.com.doghero.dhproject.data.api.ApiAnswer
import br.com.doghero.dhproject.model.MyHeroesResponse

class MyHeroesRepository {

    fun getMyHeroes() : MyHeroesResponse {
        return MyHeroes.build(ApiAnswer.myHeroes)
    }

    object MyHerosRepositoryProvider {
        fun provideMyHeroesRepository(): MyHeroesRepository {
            return MyHeroesRepository()
        }
    }
}