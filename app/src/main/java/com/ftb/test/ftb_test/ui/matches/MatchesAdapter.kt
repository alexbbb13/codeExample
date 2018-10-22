package com.ftb.test.ftb_test.ui.matches

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ftb.test.ftb_test.R
import com.ftb.test.ftb_test.data.models.MatchesBase
import android.text.method.TextKeyListener.clear
import android.support.v7.util.DiffUtil

class MatchesAdapter(val listener: (MatchesBase) -> Unit) :
        RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    private var myDataset: List<MatchesBase> = mutableListOf()

    class ViewHolder(val layout: View,
                     val team1: TextView,
                     val team2: TextView,
                     val prediction1: TextView,
                     val predictionDash: TextView,
                     val prediction2: TextView
                     ) : RecyclerView.ViewHolder(layout){

        fun bind(item: MatchesBase, listener: (MatchesBase) -> Unit) {
            itemView.setOnClickListener { _ ->listener(item) }
        }

        fun setPredictionVisibility(visibility: Int){
            prediction1.visibility = visibility
            prediction2.visibility = visibility
            predictionDash.visibility = visibility
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MatchesAdapter.ViewHolder {
        // create a new view
        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_item_matches, parent, false)
        val team1 = root.findViewById<TextView>(R.id.tv_team1)
        val team2 = root.findViewById<TextView>(R.id.tv_team2)
        val prediction1 = root.findViewById<TextView>(R.id.tv_prediction1)
        val prediction2 = root.findViewById<TextView>(R.id.tv_prediction2)
        val predictionDash = root.findViewById<TextView>(R.id.tv_dash_lower)
        // set the view's size, margins, paddings and layout parameters

        return ViewHolder(root, team1, team2, prediction1, predictionDash, prediction2)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val item  = myDataset[position]
        holder.team1.text = item.team1
        holder.team2.text = item.team2
        if (item.team1_prediction == null || item.team1_prediction == -1){
           holder.setPredictionVisibility(View.INVISIBLE)
        } else {
            holder.setPredictionVisibility(View.VISIBLE)
            item.team1_prediction?.let{setText(holder.prediction1, it)}
            item.team2_prediction?.let{setText(holder.prediction2, it) }
        }
        holder.bind(item, listener);
    }

    override fun getItemCount() = myDataset.size

    fun setData (data: List<MatchesBase>){
        myDataset = data
        notifyDataSetChanged()
    }

//    fun setData (data: List<MatchesBase>) {
//        val diffCallback = MatchesDiffCallback(myDataset, data)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        myDataset.clear()
//        myDataset.addAll(data)
//        diffResult.dispatchUpdatesTo(this)
//    }

    fun setText(view: TextView, value: Int){
        val text = if(value < 0) "" else value.toString()
        view.text = text
    }
}