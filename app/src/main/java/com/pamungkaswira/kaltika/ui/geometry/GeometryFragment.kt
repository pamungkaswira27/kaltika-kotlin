package com.pamungkaswira.kaltika.ui.geometry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pamungkaswira.kaltika.R
import com.pamungkaswira.kaltika.databinding.FragmentGeometryBinding
import com.pamungkaswira.kaltika.model.GeometryViewModel

class GeometryFragment : Fragment() {
    private lateinit var binding: FragmentGeometryBinding

    private val viewModel: GeometryViewModel by lazy {
        ViewModelProvider(requireActivity())[GeometryViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGeometryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hasilCardView.visibility = View.GONE

        binding.barisanButton.setOnClickListener { hitungBarisan() }
        viewModel.getHasilBarisan().observe(requireActivity()) { showBarisan(it) }

        binding.deretButton.setOnClickListener { hitungDeret() }
        viewModel.getHasilDeret().observe(requireActivity()) { showDeret(it) }
    }

    private fun hitungBarisan() {
        val sukuPertama = binding.sukuPertamaTextInputEditText.text.toString()
        val rasio = binding.rasioTextInputEditText.text.toString()
        val sukuN = binding.sukuNTextInputEditText.text.toString()

        if (sukuPertama.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.suku_pertama_feedback), Toast.LENGTH_LONG).show()
            return
        }
        else if (rasio.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.rasio_feedback), Toast.LENGTH_LONG).show()
            return
        }
        else if (sukuN.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.suku_n_feedback), Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungBarisan(sukuPertama.toDouble(), rasio.toDouble(), sukuN.toDouble())
    }

    private fun showBarisan(result: Double?) {
        if (result == null) return

        binding.headerHasilTextView.text = getString(R.string.header_hasil_barisan)
        binding.hasilTextView.text = getString(R.string.hasil_barisan_deret, result)

        binding.hasilCardView.visibility = View.VISIBLE
    }

    private fun hitungDeret() {
        val sukuPertama = binding.sukuPertamaTextInputEditText.text.toString()
        val rasio = binding.rasioTextInputEditText.text.toString()
        val sukuN = binding.sukuNTextInputEditText.text.toString()

        if (sukuPertama.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.suku_pertama_feedback), Toast.LENGTH_LONG).show()
            return
        }
        else if (rasio.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.rasio_feedback), Toast.LENGTH_LONG).show()
            return
        }
        else if (sukuN.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.suku_n_feedback), Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungDeret(sukuPertama.toDouble(), rasio.toDouble(), sukuN.toDouble())
    }

    private fun showDeret(result: Double?) {
        if (result == null) return

        binding.headerHasilTextView.text = getString(R.string.header_hasil_deret)
        binding.hasilTextView.text = getString(R.string.hasil_barisan_deret, result)

        binding.hasilCardView.visibility = View.VISIBLE
    }
}