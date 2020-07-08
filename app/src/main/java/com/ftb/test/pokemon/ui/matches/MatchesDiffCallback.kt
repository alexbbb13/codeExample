package com.ftb.test.pokemon.ui.matches

import android.support.annotation.Nullable
import android.support.v7.util.DiffUtil
import android.util.Log
import com.ftb.test.pokemon.data.models.MatchesBase


class MatchesDiffCallback(private val mOldMatchList: List<MatchesBase>, private val mNewMatchList: List<MatchesBase>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldMatchList.size
    }

    override fun getNewListSize(): Int {
        return mNewMatchList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMatch = mOldMatchList[oldItemPosition]
        val newMatch = mNewMatchList[newItemPosition]
        if (oldMatch.matchHash != newMatch.matchHash) return false
        return true
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMatch = mOldMatchList[oldItemPosition]
        val newMatch = mNewMatchList[newItemPosition]

        if (!oldMatch.matchHash.equals(newMatch.matchHash)) return false
        logData("areContentsTheSame", oldMatch, newMatch)
        //not overriding predictions from database
        return !(shouldUpdate(oldMatch.team1_prediction, newMatch.team1_prediction) &&
                shouldUpdate(oldMatch.team2_prediction, newMatch.team2_prediction))
    }

    fun logData(text: String, oldMatch: MatchesBase, newMatch: MatchesBase){
        val flag = !(shouldUpdate(oldMatch.team1_prediction, newMatch.team1_prediction) &&
                shouldUpdate(oldMatch.team2_prediction, newMatch.team2_prediction))
        Log.d("TTT", "${text} ${oldMatch.team1}-${oldMatch.team2} " +
                "OLD ${oldMatch.team1_prediction}-${oldMatch.team2_prediction}" +
                " VS NEW ${newMatch.team1_prediction}-${newMatch.team2_prediction} - ${flag}" )
   }

    fun shouldUpdate(old: Int?, new: Int?): Boolean {
        if( new == null || new == -1 ) return false;
        if( old == null || old == -1 ) return true;
        return old != new
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}