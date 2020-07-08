package com.ftb.test.pokemon.di.app

import com.ftb.test.pokemon.di.matches.MatchesScreenModule
import com.ftb.test.pokemon.di.results.ResultsScreenModule
import com.ftb.test.pokemon.ui.matches.MatchesFragment
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


    @ContributesAndroidInjector(modules = [MatchesScreenModule::class])
    abstract fun bindMatchesFragment(): MatchesFragment

    @ContributesAndroidInjector(modules = [ResultsScreenModule::class])
    abstract fun bindResultsFragment(): ResultsFragment
}
