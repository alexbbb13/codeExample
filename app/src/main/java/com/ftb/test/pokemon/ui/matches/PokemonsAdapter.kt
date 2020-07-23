package com.ftb.test.pokemon.ui.matches

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ftb.test.pokemon.R
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.data.models.PokemonBaseLoading
import com.squareup.picasso.Picasso




class PokemonsAdapter(val listener: (PokemonBase) -> Unit, val picasso: Picasso) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val VIEW_TYPE_ITEM = 0
        private val VIEW_TYPE_LOADING = 1
    }
    private var myDataset: List<PokemonBase> = mutableListOf()

    class PokemonViewHolder(layout: View,
                     val name: TextView,
                     val image: ImageView
                     ) : RecyclerView.ViewHolder(layout){

        fun bind(item: PokemonBase, listener: (PokemonBase) -> Unit) {
            itemView.setOnClickListener { _ ->listener(item) }
        }

        fun cleanup(picasso: Picasso) {
            //picasso.cancelRequest(image)
            image.setImageDrawable(null)
        }
    }

    class LoadingViewHolder(layout: View)   : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_TYPE_ITEM -> {
                val root = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_list_item_pokemon, parent, false)
                val name = root.findViewById<TextView>(R.id.poke_name)
                val imageView = root.findViewById<ImageView>(R.id.poke_image)
                return PokemonViewHolder(root, name, imageView)
            }
            VIEW_TYPE_LOADING -> {
                val root = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_list_item_loading, parent, false)
                return LoadingViewHolder(root)
            }
        }
        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_item_pokemon, parent, false)
        val name = root.findViewById<TextView>(R.id.poke_name)
        val imageView = root.findViewById<ImageView>(R.id.poke_image)
        return PokemonViewHolder(root, name, imageView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PokemonViewHolder -> {
                val item  = myDataset[position]
                holder.name.text = item.name
                if (item.pictureUrl.isNullOrEmpty().not()) {
                    picasso.load(item.pictureUrl).fit().into(holder.image)
                }
                holder.bind(item, listener);
            }
            is LoadingViewHolder -> {
                //Do nothing
            }
        }
    }
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if(holder is PokemonViewHolder) holder.cleanup(picasso)
    }

    override fun getItemViewType(position: Int): Int {
        return if (myDataset.get(position) is PokemonBaseLoading) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun getItemCount() = myDataset.size

    fun setData (data: List<PokemonBase>){
        myDataset = data
    }

//    fun setData (data: List<MatchesBase>) {
//        val d iffCallback = MatchesDiffCallback(myDataset, data)
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