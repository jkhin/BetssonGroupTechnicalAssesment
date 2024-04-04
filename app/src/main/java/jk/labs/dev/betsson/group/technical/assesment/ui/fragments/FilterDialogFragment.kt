package jk.labs.dev.betsson.group.technical.assesment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import jk.labs.dev.betsson.group.technical.assesment.R
import jk.labs.dev.betsson.group.technical.assesment.databinding.FragmentDialogFiltersBinding
import jk.labs.dev.betsson.group.technical.assesment.domain.entities.PricingScale
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlacesFilterModel


class FilterDialogFragment: DialogFragment() {

    private lateinit var listener: FilterPlacesListener

    private var isOpenNowToggleSelected: Boolean? = null

    interface FilterPlacesListener {
        fun doOnAppliedFilter(filterModel: PlacesFilterModel)
    }

    private lateinit var binding: FragmentDialogFiltersBinding

    override fun onResume() {
        super.onResume()
        setupViews()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupEvents()
    }

    private fun setupViews() = with(binding) {
        val pricingScale = PricingScale.entries.map { it.rangeValue }
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, pricingScale)
        autoCompleteTextView.setAdapter(arrayAdapter)
    }

    private fun setupEvents() = with(binding) {
        btbFilterDialogApply.setOnClickListener {
            if (::listener.isInitialized) {
                val filterModel = buildAndGetPlacesFilterModel()
                listener.doOnAppliedFilter(filterModel)
            }
            dismiss()
        }
        btbFilterDialogCancel.setOnClickListener {
            dismiss()
        }

        tvFilterDialogOpenNowToggle.setOnCheckedChangeListener { _, isChecked ->
            isOpenNowToggleSelected = isChecked
        }
    }

    private fun buildAndGetPlacesFilterModel(): PlacesFilterModel {
        return PlacesFilterModel(
            priceScale = when(binding.tvFilterDialogDropdown.editText?.text.toString()) {
                PricingScale.MOST_AFFORDABLE.rangeValue -> PricingScale.MOST_AFFORDABLE
                PricingScale.AFFORDABLE.rangeValue -> PricingScale.AFFORDABLE
                PricingScale.EXPENSIVE.rangeValue -> PricingScale.EXPENSIVE
                PricingScale.MOST_EXPENSIVE.rangeValue -> PricingScale.MOST_EXPENSIVE
                else -> null
            },
            isOpen = isOpenNowToggleSelected
        )
    }


    fun setListener(listener: FilterPlacesListener) {
        this.listener = listener
    }
}