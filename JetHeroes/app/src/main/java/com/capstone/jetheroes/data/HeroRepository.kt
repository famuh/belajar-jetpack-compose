package com.capstone.jetheroes.data

import com.capstone.jetheroes.model.Hero
import com.capstone.jetheroes.model.HeroesData

class HeroRepository {
    fun getHeroes(): List<Hero>{
        return HeroesData.heroes
    }
}