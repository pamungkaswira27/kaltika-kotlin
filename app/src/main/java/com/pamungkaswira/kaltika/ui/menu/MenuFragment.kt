package com.pamungkaswira.kaltika.ui.menu

import android.content.Intent
import android.os.Bundle
import android.view.*
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

        setHasOptionsMenu(true)

        binding.openCubeImageView.setOnClickListener {
            it.findNavController().navigate(R.id.menuFragment_to_CubeFragment)
        }

        binding.openCuboidImageView.setOnClickListener {
            it.findNavController().navigate(R.id.menuFragment_to_CuboidFragment)
        }

        binding.openArithmeticImageView.setOnClickListener {
            it.findNavController().navigate(R.id.menuFragment_to_ArithmeticFragment)
        }

        binding.openGeometryImageView.setOnClickListener {
            it.findNavController().navigate(R.id.menuFragment_to_GeometryFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuShare) {
            val message = getString(R.string.share_template)
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)

            if (shareIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(shareIntent)
            }

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}