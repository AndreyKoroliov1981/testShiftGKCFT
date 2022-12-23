package com.korolev.test.ui.home

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.korolev.domain.home.model.Record
import com.korolev.test.R
import com.korolev.test.app.App
import com.korolev.test.common.IsHomeDataNetwork
import com.korolev.test.common.IsNotHomeDataNetwork
import com.korolev.test.databinding.FragmentHomeBinding
import com.korolev.test.ui.home.recycler.RVOnClickBasketListener
import com.korolev.test.ui.home.recycler.RecordRVAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewBinding: FragmentHomeBinding by viewBinding()

    @javax.inject.Inject
    lateinit var vmFactory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel

    private var recordRVAdapter = RecordRVAdapter(
        object : RVOnClickBasketListener {
            override fun onClicked(item: Record) {
                viewModel.onClickDeleteRequest(item)
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.applicationContext as App).appComponent.injectHomeFragment(this)
        viewModel = ViewModelProvider(this, vmFactory).get(HomeViewModel::class.java)
        initListeners()
        viewBinding.rvListDB.adapter = recordRVAdapter
        viewBinding.apply {
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        viewModel.stateFlow.collect {
                            if (it.records == emptyList<Record>()) {
                                rvListDB.isVisible = false
                                tvNoDataInDB.isVisible = true
                            } else {
                                recordRVAdapter.updateList(it.records.reversed())
                                rvListDB.isVisible = true
                                tvNoDataInDB.isVisible = false
                            }
                        }
                    }

                    launch {
                        viewModel.sideEffect.collectLatest {
                            if (it is IsNotHomeDataNetwork) {
                                writeError(view, it.errorMessage)
                            }
                            if (it is IsHomeDataNetwork) {
                                val action =
                                    HomeFragmentDirections.actionFragmentHomeToFragmentDetail(it.data)
                                Navigation.findNavController(viewBinding.root).navigate(action)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initListeners() {
        viewBinding.svSearchBin.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.onChangeSearchBin(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.onClickSendRequest()
                return false
            }
        })

        viewBinding.btnSendRequest.setOnClickListener {
            viewModel.onClickSendRequest()
        }
        viewBinding.btnDeleteAllList.setOnClickListener {
            viewModel.onClickDeleteAllList()
        }
    }

    private fun writeError(view: View, error: String) {
        val snackBarView =
            Snackbar.make(view, error, Snackbar.LENGTH_LONG)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.error_text))
                .setAction("Retry") {
                    viewModel.onClickSendRequest()
                }
        val sbView = snackBarView.view
        val params = sbView.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        sbView.layoutParams = params
        snackBarView.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        snackBarView.show()
    }
}