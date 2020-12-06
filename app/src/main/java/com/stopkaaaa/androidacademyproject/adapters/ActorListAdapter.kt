package com.stopkaaaa.androidacademyproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stopkaaaa.androidacademyproject.data.models.Actor
import com.stopkaaaa.androidacademyproject.databinding.ViewHolderActorBinding

class ActorListAdapter() : RecyclerView.Adapter<ActorViewHolder>() {

    lateinit var actorsRecyclerView: RecyclerView
    private var actors: List<Actor> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding = ViewHolderActorBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        val holder: ActorViewHolder = ActorViewHolder(binding)
        holder.itemView.layoutParams.width = ((actorsRecyclerView.measuredWidth -
                parent.context.resources.displayMetrics.density * 24.0 ) / 4).toInt()

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
        actors = newList
    }

}

class ActorViewHolder(private val binding: ViewHolderActorBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(actor: Actor) {
        binding.actorName.text = actor.name
        binding.actorPhoto.setImageResource(actor.photo)
    }
}