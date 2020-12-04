package com.stopkaaaa.androidacademyproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stopkaaaa.androidacademyproject.databinding.FragmentMoviesDetailsBinding
import com.stopkaaaa.androidacademyproject.databinding.FragmentMoviesListBinding

class FragmentMoviesDetails : Fragment() {


    lateinit var binding: FragmentMoviesDetailsBinding
    private var listener: ClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            listener?.backPressed()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is ClickListener) {
            this.listener = activity as ClickListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}