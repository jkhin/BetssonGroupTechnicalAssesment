package jk.labs.dev.betsson.group.technical.assesment.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import jk.labs.dev.betsson.group.technical.assesment.R
import jk.labs.dev.betsson.group.technical.assesment.databinding.FragmentNearbyPlacesBinding
import jk.labs.dev.betsson.group.technical.assesment.ui.MainActivity
import jk.labs.dev.betsson.group.technical.assesment.ui.adapters.NearbyPlacesAdapter
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlaceGridItemModel
import jk.labs.dev.betsson.group.technical.assesment.ui.models.PlacesFilterModel
import jk.labs.dev.betsson.group.technical.assesment.ui.states.PlacesUiState
import jk.labs.dev.betsson.group.technical.assesment.ui.viewmodels.LoadingViewModel
import jk.labs.dev.betsson.group.technical.assesment.ui.viewmodels.NearbyPlacesViewModel
import jk.labs.dev.betsson.group.technical.assesment.utils.decorators.GridItemDecorator

@AndroidEntryPoint
class NearbyPlacesFragment : Fragment(), FilterDialogFragment.FilterPlacesListener {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest

    private lateinit var binding: FragmentNearbyPlacesBinding
    private lateinit var dialog: FilterDialogFragment

    private val viewModel: NearbyPlacesViewModel by viewModels()
    private val loadingViewModel by activityViewModels<LoadingViewModel>()


    private val gridAdapter by lazy { NearbyPlacesAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNearbyPlacesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupEvents()
        setupObservers()
        getNearbyPlaces()
    }

    private fun setupViews() = with(binding) {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        rvGridPlaces.apply {
            adapter = gridAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(
                GridItemDecorator(requireContext().resources.getDimension(R.dimen.dp_16).toInt())
            )
        }
    }

    private fun setupEvents() = with(binding) {
        btnFilter.setOnClickListener {
            if (!::dialog.isInitialized) {
                dialog = FilterDialogFragment()
                dialog.setListener(this@NearbyPlacesFragment)
            }
            if (!dialog.isVisible) dialog.show(
                childFragmentManager,
                FilterDialogFragment::class.java.name
            )
        }
    }

    private fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is PlacesUiState.Loading -> doOnLoading(it.isLoading)
                is PlacesUiState.Success -> doOnSuccess(it.items)
                is PlacesUiState.Error -> doOnError(it.errorMessage)
            }
        }
    }

    private fun doOnLoading(isLoading: Boolean) {
        loadingViewModel.updateLoadingUiState(isLoading)
    }

    private fun doOnSuccess(items: List<PlaceGridItemModel>) {
        gridAdapter.submitList(items.toMutableList())
    }

    private fun doOnError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun doOnAppliedFilter(filterModel: PlacesFilterModel) {
        viewModel.filterNearbyPlaces(filterModel)
    }

    private fun getNearbyPlaces() {
        getLastLocation()
        viewModel.getNearbyPlaces()
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    val location = task.result
                    if (location == null) {
                        getNewLocation()
                    } else {
                        viewModel.setCurrentLocation(
                            Pair(location.latitude, location.longitude)
                        )
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please enable your Location service",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getNewLocation() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            p0.lastLocation?.let {
                viewModel.setCurrentLocation(
                    Pair(it.latitude, it.longitude)
                )
            }
        }
    }

    private fun checkPermissions(): Boolean {
        return (ActivityCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
            ),
            PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(
            LocationManager.GPS_PROVIDER
        ) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    companion object {
        private const val PERMISSION_ID = 10001
    }

}