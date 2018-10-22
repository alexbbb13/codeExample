package com.ftb.test.ftb_test.ui.results

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.ftb.test.ftb_test.R
import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseDb
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.ResultBase

class ResultsAdapter() :
        RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {
    private var myDataset: List<ResultBase> = emptyList()

    class ViewHolder(val relativeLayout: RelativeLayout,
                     val team1: TextView,
                     val team2: TextView,
                     val score1: TextView,
                     val score2: TextView,
                     val prediction1: TextView,
                     val prediction2: TextView
                     ) : RecyclerView.ViewHolder(relativeLayout){

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ResultsAdapter.ViewHolder {
        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_item_results, parent, false) as RelativeLayout
        val team1 = root.findViewById<TextView>(R.id.tv_team1)
        val team2 = root.findViewById<TextView>(R.id.tv_team2)
        val score1 = root.findViewById<TextView>(R.id.tv_score1)
        val score2 = root.findViewById<TextView>(R.id.tv_score2)

        val prediction1 = root.findViewById<TextView>(R.id.tv_prediction1)
        val prediction2 = root.findViewById<TextView>(R.id.tv_prediction2)

        return ViewHolder(root, team1, team2, score1, score2, prediction1, prediction2)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val item  = myDataset[position]
        holder.team1.text = item.team1
        holder.team2.text = item.team2
        setText(holder.score1, item.team1_points)
        setText(holder.score2, item.team2_points)
        setText(holder.prediction1, item.team1_prediction)
        setText(holder.prediction2, item.team2_prediction)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

    fun setData (data: List<ResultBase>){
        myDataset = data
    }

    fun setText(view: TextView, value: Int){
        val text = if(value < 0) value.toString() else "?"
        view.text = text
    }

}