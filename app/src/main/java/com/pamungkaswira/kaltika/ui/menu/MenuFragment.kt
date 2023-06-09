package com.pamungkaswira.kaltika.ui.menu

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pamungkaswira.kaltika.MainActivity
import com.pamungkaswira.kaltika.R
import com.pamungkaswira.kaltika.data.SettingsDataStore
import com.pamungkaswira.kaltika.data.dataStore
import com.pamungkaswira.kaltika.databinding.FragmentMenuBinding
import com.pamungkaswira.kaltika.model.menu.MenuViewModel
import com.pamungkaswira.kaltika.network.ApiStatus
import kotlinx.coroutines.launch

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var listMenuAdapter: ListMenuAdapter
    private lateinit var gridMenuAdapter: GridMenuAdapter

    private val viewModel: MenuViewModel by lazy {
        ViewModelProvider(this)[MenuViewModel::class.java]
    }

    private val layoutDataStore: SettingsDataStore by lazy {
        SettingsDataStore(requireContext().dataStore)
    }

    private var isLinearLayout = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)

        listMenuAdapter = ListMenuAdapter()
        gridMenuAdapter = GridMenuAdapter()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutDataStore.preferenceFlow.asLiveData().observe(viewLifecycleOwner) {
            isLinearLayout = it
            setAdapter()
            setLayout()
            setIcon()
        }

        binding.toggleViewImageView.setOnClickListener { toggleLayout() }

        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }

        viewModel.scheduleUpdater(requireActivity().application)
//        binding.openCubeImageView.setOnClickListener {
//            it.findNavController().navigate(R.id.menuFragment_to_CubeFragment)
//        }
//
//        binding.openCuboidImageView.setOnClickListener {
//            it.findNavController().navigate(R.id.menuFragment_to_CuboidFragment)
//        }
//
//        binding.openArithmeticImageView.setOnClickListener {
//            it.findNavController().navigate(R.id.menuFragment_to_ArithmeticFragment)
//        }
//
//        binding.openGeometryImageView.setOnClickListener {
//            it.findNavController().navigate(R.id.menuFragment_to_GeometryFragment)
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuHistory) {
            findNavController().navigate(R.id.menuFragment_to_HistoryFragment)
            return true
        }
        else if (item.itemId == R.id.menuShare) {
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

    private fun toggleLayout() {
        lifecycleScope.launch {
            layoutDataStore.saveLayout(!isLinearLayout, requireContext())
        }
    }

    private fun setAdapter() {
        with(binding.menuRecyclerView) {
            adapter = if (isLinearLayout) listMenuAdapter else gridMenuAdapter
            setHasFixedSize(true)
        }

        viewModel.getData().observe(viewLifecycleOwner) {
            if (isLinearLayout)
                listMenuAdapter.updateData(it)
            else
                gridMenuAdapter.updateData(it)
        }
    }

    private fun setLayout() {
        binding.menuRecyclerView.layoutManager = if (isLinearLayout)
            LinearLayoutManager(context)
        else
            GridLayoutManager(context, 2)
    }

    private fun setIcon() {
        if (isLinearLayout) {
            binding.toggleViewImageView.setImageResource(R.drawable.grid)
        }
        else {
            binding.toggleViewImageView.setImageResource(R.drawable.list)
        }
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestNotificationPermission()
                }
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkErrorTextView.visibility = View.VISIBLE
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                MainActivity.PERMISSION_REQUEST_CODE
            )
        }
    }
}