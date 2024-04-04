package jk.labs.dev.betsson.group.technical.assesment.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import jk.labs.dev.betsson.group.technical.assesment.R
import jk.labs.dev.betsson.group.technical.assesment.databinding.FragmentPlaceDetailBinding
import jk.labs.dev.betsson.group.technical.assesment.ui.MainActivity
import jk.labs.dev.betsson.group.technical.assesment.ui.adapters.PlacePhotosAdapter
import jk.labs.dev.betsson.group.technical.assesment.ui.adapters.PlaceTipsAdapter
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PhotoModel
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceDetailModel
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceTipModel
import jk.labs.dev.betsson.group.technical.assesment.ui.states.PlaceDetailUiState
import jk.labs.dev.betsson.group.technical.assesment.ui.viewmodels.LoadingViewModel
import jk.labs.dev.betsson.group.technical.assesment.ui.viewmodels.PlaceDetailViewModel
import jk.labs.dev.betsson.group.technical.assesment.utils.decorators.PlacePhotoItemDecorator
import jk.labs.dev.betsson.group.technical.assesment.utils.listeners.BasicObserveListener
import jk.labs.dev.betsson.group.technical.assesment.utils.listeners.SetupFragmentListener

@AndroidEntryPoint
class PlaceDetailsFragment : Fragment(),
    BasicObserveListener<Triple<PlaceDetailModel, List<PhotoModel>,
            List<PlaceTipModel>>>,
    SetupFragmentListener {

    private lateinit var binding: FragmentPlaceDetailBinding

    private lateinit var menuActionBar: Menu


    private val viewModel by viewModels<PlaceDetailViewModel>()
    private val loadingViewModel by activityViewModels<LoadingViewModel>()

    private val placePhotosAdapter by lazy { PlacePhotosAdapter() }
    private val placeTipsAdapter by lazy { PlaceTipsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaceDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupEvents()
        setupObservers()
        viewModel.getPlaceDetail()
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_place_details, menu)
        menuActionBar = menu
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemFavorite -> {
                val isFavorite =
                    menuActionBar.findItem(R.id.menuItemFavorite).icon == ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_favorite_filled
                    )
                viewModel.setPlaceAsFavorite(!isFavorite)
                setIconAsFavorite(!isFavorite)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setupViews(): Unit = with(binding) {
        rvPlaceDetailGallery.apply {
            adapter = placePhotosAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            addItemDecoration(
                PlacePhotoItemDecorator(
                    requireContext().resources.getDimension(R.dimen.dp_2).toInt()
                )
            )
        }

        rvPlaceDetailReviews.apply {
            adapter = placeTipsAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    override fun setupObservers() {
        viewModel.placeDetailsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is PlaceDetailUiState.Loading -> doOnLoading(it.isLoading)
                is PlaceDetailUiState.Success -> doOnSuccess(it.model)
                is PlaceDetailUiState.Error -> doOnError(it.errorMessage)
            }
        }
    }

    override fun setupEvents() = with(binding) {
        fabPlaceDetailCall.setOnClickListener {
            val uri = Uri.parse("tel:${viewModel.getPlacePhoneNumber()}")
            val intent = Intent(Intent.ACTION_DIAL).setData(uri)
            startActivity(intent)
        }
    }

    override fun init() {
        setupViews()
        setupEvents()
        setupObservers()
        viewModel.getPlaceDetail()
    }

    override fun doOnSuccess(
        model: Triple<PlaceDetailModel, List<PhotoModel>, List<PlaceTipModel>>
    ) = with(binding) {
        handleFabVisibility()
        tvPlaceDetailName.text = model.first.name
        tvPlaceDetailAddress.text = model.first.address
        tvPlaceDetailAvailability.text = model.first.isOpenNow
        tvPlaceDetailPricing.text = model.first.pricingScale.rangeValue
        tvPlaceDetailRating.text = model.first.rating

        placePhotosAdapter.submitList(model.second)
        placeTipsAdapter.submitList(model.third)
        setIconAsFavorite(model.first.isFavorite)
    }

    override fun doOnLoading(isLoading: Boolean) {
        loadingViewModel.updateLoadingUiState(isLoading)
        // (requireActivity() as? MainActivity)?.updateLoading(isLoading)
    }


    override fun doOnError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun handleFabVisibility() = with(binding.fabPlaceDetailCall) {
        visibility = if (viewModel.getPlacePhoneNumber().isBlank()) View.GONE else View.VISIBLE
    }

    private fun setIconAsFavorite(isFavorite: Boolean) {
        val drawableId = if (isFavorite) R.drawable.ic_favorite_filled
        else R.drawable.ic_favorite_border
        menuActionBar.findItem(R.id.menuItemFavorite)?.icon =
            requireContext().let { ContextCompat.getDrawable(it, drawableId) }
    }

}