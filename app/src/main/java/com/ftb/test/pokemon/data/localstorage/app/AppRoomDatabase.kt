package com.ftb.test.pokemon.data.localstorage.app

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ftb.test.pokemon.data.localstorage.matches.PokemonBaseDb
import com.ftb.test.pokemon.data.localstorage.matches.PokemonsDao
import com.ftb.test.pokemon.data.localstorage.matches.PredictionsBaseDb
import com.ftb.test.pokemon.data.localstorage.matches.PredictionsDao
import com.ftb.test.pokemon.data.localstorage.results.ResultsBaseDb
import com.ftb.test.pokemon.data.localstorage.results.ResultsDao

@Database(entities = [
    PokemonBaseDb::class,
    ResultsBaseDb::class,
    PredictionsBaseDb::class
], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun getPokemonsDao(): PokemonsDao
    abstract fun getResultsDao(): ResultsDao   //Это к покемонам не относится, это шаблон приложения, который дорабатываю периодически и использую для задач типа "напиши приложение по быстрому" итд.
                                               //Кстати, буду благодарен за фидбек на alex_bbb@mail.ru.
    abstract fun getPredictionsDao(): PredictionsDao //Same template
}