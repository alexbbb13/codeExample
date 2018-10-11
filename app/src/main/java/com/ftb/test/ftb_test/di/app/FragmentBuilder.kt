package com.ftb.test.ftb_test.di.app

import com.ftb.test.ftb_test.di.matches.MatchesScreenModule
import com.ftb.test.ftb_test.di.results.ResultsScreenModule
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
    abstract fun bindSkbProductFragment(): MatchesFragment

    @ContributesAndroidInjector(modules = [ResultsScreenModule::class])
    abstract fun bindTransferChoiceFragment(): ResultsFragment
}
