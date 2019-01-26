package br.com.doghero.dhproject.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import br.com.doghero.dhproject.model.MyHeroesResponse
import br.com.doghero.dhproject.repository.MyHeroesRepository

class MyHeroesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MyHeroesRepository.MyHerosRepositoryProvider.provideMyHeroesRepository()
    private lateinit var myHeroesResult: MutableLiveData<MyHeroesResponse>

    fun getMyHeroesObservable(): MutableLiveData<MyHeroesResponse> {
        if (!::myHeroesResult.isInitialized) {
            myHeroesResult = MutableLiveData()
        }
        return myHeroesResult
    }

    fun getMyHeroes() {
        val myHeroes = repository.getMyHeroes()
        myHeroesResult.value = myHeroes
    }
}