package br.com.doghero.dhproject.model

data class MyHeroesResponse(
        var recents: List<Hero>,
        var favorites: List<Hero>
)