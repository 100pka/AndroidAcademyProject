package com.stopkaaaa.androidacademyproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stopkaaaa.androidacademyproject.databinding.FragmentMoviesListBinding

class FragmentMoviesList: Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private var listener: ClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movie1.setOnClickListener {
            listener?.movieClicked()
        }
        binding.movie2.setOnClickListener {
            listener?.movieClicked()
        }
        binding.movie3.setOnClickListener {
            listener?.movieClicked()
        }
        binding.movie4.setOnClickListener {
            listener?.movieClicked()
        }
    }

    fun setListener(l: ClickListener) {
        listener = l
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.listener = activity as ClickListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
