package com.capstone.jetheroes.data

import com.capstone.jetheroes.model.Hero
import com.capstone.jetheroes.model.HeroesData

class HeroRepository {
    fun getHeroes(): List<Hero>{
        return HeroesData.heroes
    }

    fun searchHeroes(query: String): List<Hero> {
        return HeroesData.heroes.filter {
            it.name.contains(query, ignoreCase = true)
            // ignoreCase digunakan supaya proses pencarian tidak memperhatikan besar kecilnya huruf.
        }
    }
}