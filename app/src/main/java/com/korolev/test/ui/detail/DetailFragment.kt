package com.korolev.test.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.korolev.domain.home.model.Record
import com.korolev.test.R
import com.korolev.test.app.App
import com.korolev.test.common.IsHomeDataNetwork
import com.korolev.test.common.IsNotHomeDataNetwork
import com.korolev.test.databinding.FragmentDetailBinding
import com.korolev.test.ui.home.HomeFragmentDirections
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val viewBinding: FragmentDetailBinding by viewBinding()

    @javax.inject.Inject
    lateinit var vmFactoryDF: DetailViewModel.DetailViewModelFactory

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.providesFactory(
            assistedFactory = vmFactoryDF,
            recordId = DetailFragmentArgs.fromBundle(requireArguments()).idRecord
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.applicationContext as App).appComponent.injectDetailFragment(this)
        initListeners()
        viewBinding.apply {
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        viewModel.stateFlow.collect {
                            if (it.record != null) fillContent(it.record)
                        }
                    }
                }
            }
        }
    }

    private fun initListeners() {
        viewBinding.btnBack.setOnClickListener {
            val action = DetailFragmentDirections.actionFragmentDetailToFragmentHome()
            Navigation.findNavController(viewBinding.root).navigate(action)
        }
        viewBinding.tvBankPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${viewModel.stateFlow.value.record?.binInfo?.bank?.phone}"))
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent);
            }
        }
        viewBinding.tvBankUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://${viewModel.stateFlow.value.record?.binInfo?.bank?.url}"))
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                Log.d("my_tag","tvBankUrl setOnClickListener")
                startActivity(intent)
            }
        }
        viewBinding.tvLatitude.setOnClickListener {
            startMap()
        }
        viewBinding.tvLongitude.setOnClickListener {
            startMap()
        }
    }

    private fun startMap() {
        val geoUriString = "geo:${viewModel.stateFlow.value.record?.binInfo?.country?.latitude},${viewModel.stateFlow.value.record?.binInfo?.country?.longitude}?z=10"
        val geoUri: Uri = Uri.parse(geoUriString)
        val mapIntent = Intent(Intent.ACTION_VIEW, geoUri)
        if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(mapIntent)
        }
    }

    private fun fillContent(record: Record) {
        val notDefined = getString(R.string.text_not_defined)
        with(viewBinding) {
            tvBinBankCard.text = record.bin
            tvSheme.text = record.binInfo?.scheme ?: notDefined
            tvBrand.text = record.binInfo?.brand ?: notDefined
            tvType.text = record.binInfo?.type ?: notDefined
            if (record.binInfo?.country != null) {
                tvTextCountryName.isVisible = true
                if (record.binInfo?.country?.name == null) tvCountryName.isVisible =
                    false
                else {
                    tvCountryName.isVisible = true
                    tvCountryName.text = record.binInfo?.country?.name
                }
                if (record.binInfo?.country?.currency == null) tvCurrency.isVisible =
                    false
                else {
                    tvCurrency.isVisible = true
                    tvCurrency.text = getString(
                        R.string.currency,
                        record.binInfo?.country?.currency
                    )
                }
                if (record.binInfo?.country?.latitude == null) tvLatitude.isVisible =
                    false
                else {
                    tvLatitude.isVisible = true
                    tvLatitude.text = getString(
                        R.string.latitude,
                        record.binInfo?.country?.latitude.toString()
                    )
                }
                if (record.binInfo?.country?.longitude == null) tvLongitude.isVisible =
                    false
                else {
                    tvLongitude.isVisible = true
                    tvLongitude.text = getString(
                        R.string.longitude,
                        record.binInfo?.country?.longitude.toString()
                    )
                }
            } else {
                tvTextCountryName.isVisible = false
                tvCountryName.isVisible = false
                tvCurrency.isVisible = false
                tvLatitude.isVisible = false
                tvLongitude.isVisible = false
            }
            if (record.binInfo?.bank != null) {
                tvTextBank.isVisible = true
                if (record.binInfo?.bank?.name == null) tvBankName.isVisible =
                    false
                else {
                    tvBankName.isVisible = true
                    tvBankName.text = record.binInfo?.bank?.name
                }
                if (record.binInfo?.bank?.city == null) tvBankCity.isVisible =
                    false
                else {
                    tvBankCity.isVisible = true
                    tvBankCity.text = record.binInfo?.bank?.city
                }
                if (record.binInfo?.bank?.url == null) tvBankUrl.isVisible =
                    false
                else {
                    tvBankUrl.isVisible = true
                    tvBankUrl.text = record.binInfo?.bank?.url
                }
                if (record.binInfo?.bank?.phone == null) tvBankPhone.isVisible =
                    false
                else {
                    tvBankPhone.isVisible = true
                    tvBankPhone.text = record.binInfo?.bank?.phone
                }
            } else {
                tvTextBank.isVisible = false
                tvBankName.isVisible = false
                tvBankCity.isVisible = false
                tvBankUrl.isVisible = false
                tvBankPhone.isVisible = false
            }
        }
    }
}