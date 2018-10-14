package com.ftb.test.ftb_test.ui.matches

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.ftb.test.ftb_test.R
import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseDb

class MatchesAdapter() :
        RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    private lateinit var myDataset: List<MatchesBaseDb>

    class ViewHolder(val relativeLayout: RelativeLayout,
                     val team1: TextView,
                     val team2: TextView,
                     val prediction1: TextView,
                     val prediction2: TextView
                     ) : RecyclerView.ViewHolder(relativeLayout){

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MatchesAdapter.ViewHolder {
        // create a new view
        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_item_matches, parent, false) as RelativeLayout
        val team1 = root.findViewById<TextView>(R.id.tv_team1)
        val team2 = root.findViewById<TextView>(R.id.tv_team2)
        val prediction1 = root.findViewById<TextView>(R.id.tv_prediction1)
        val prediction2 = root.findViewById<TextView>(R.id.tv_prediction2)
        // set the view's size, margins, paddings and layout parameters

        return ViewHolder(root, team1, team2, prediction1, prediction2)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val item  = myDataset[position]
        holder.team1.text = item.team1
        holder.team2.text = item.team2
        setText(holder.prediction1, item.predictionTeam1)
        setText(holder.prediction2, item.predictionTeam2)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

    fun setData (data: List<MatchesBaseDb>){
        myDataset = data
    }

    fun setText(view: TextView, value: Int){
        val text = if(value < 0) value.toString() else "?"
        view.text = text
    }

}