package com.ftb.test.pokemon.di.app

import com.ftb.test.pokemon.di.matches.PokemonsScreenModule
import com.ftb.test.pokemon.di.results.ResultsScreenModule
import com.ftb.test.pokemon.ui.matches.PokemonsFragment
import com.ftb.test.pokemon.ui.results.ResultsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentBuilder {
    /**
     * Example:
     * @ContributesAndroidInjector(modules = [NameModule::class])
     * abstract fun bindNameFragment(): NameFragment
     * */


    @ContributesAndroidInjector(modules = [PokemonsScreenModule::class])
    abstract fun bindMatchesFragment(): PokemonsFragment

    @ContributesAndroidInjector(modules = [ResultsScreenModule::class])
    abstract fun bindResultsFragment(): ResultsFragment
}
