package com.pamungkaswira.kaltika.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pamungkaswira.kaltika.databinding.FragmentCubeBinding

class CubeFragment : Fragment() {

    private lateinit var binding: FragmentCubeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCubeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}