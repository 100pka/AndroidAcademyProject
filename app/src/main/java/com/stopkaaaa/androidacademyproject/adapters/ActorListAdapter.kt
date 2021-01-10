package com.stopkaaaa.androidacademyproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.stopkaaaa.androidacademyproject.BuildConfig
import com.stopkaaaa.androidacademyproject.R
import com.stopkaaaa.androidacademyproject.data.models.Actor
import com.stopkaaaa.androidacademyproject.databinding.ViewHolderActorBinding

const val ACTORS_MARGIN = 8
const val ACTORS_COUNT_ON_SCREEN = 4

class ActorListAdapter() : RecyclerView.Adapter<ActorViewHolder>() {

    lateinit var actorsRecyclerView: RecyclerView
    private var actors: MutableList<Actor> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding = ViewHolderActorBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ActorViewHolder(binding)
        holder.itemView.layoutParams.width = ((actorsRecyclerView.measuredWidth -
                parent.context.resources.displayMetrics.density * ACTORS_MARGIN * (ACTORS_COUNT_ON_SCREEN - 1)) /
                ACTORS_COUNT_ON_SCREEN).toInt()
        return holder
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        actorsRecyclerView = recyclerView
    }

    fun bindActors(newList: List<Actor>) {
        actors.clear()
        actors.addAll(newList)
        notifyDataSetChanged()
    }

}

class ActorViewHolder(private val binding: ViewHolderActorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(actor: Actor) {
        binding.actorName.text = actor.name
        binding.actorPhoto.load(BuildConfig.TMDB_IMAGE_URL + actor.picture) {
            placeholder(R.drawable.actor_placeholder)
        }
    }
}