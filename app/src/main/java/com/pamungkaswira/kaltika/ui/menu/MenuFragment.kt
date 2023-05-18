package com.pamungkaswira.kaltika.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.pamungkaswira.kaltika.R
import com.pamungkaswira.kaltika.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.openCubeImageView.setOnClickListener {
            it.findNavController().navigate(R.id.menuFragment_to_CubeFragment)
        }

        binding.openCuboidImageView.setOnClickListener {
            it.findNavController().navigate(R.id.menuFragment_to_CuboidFragment)
        }
    }
}