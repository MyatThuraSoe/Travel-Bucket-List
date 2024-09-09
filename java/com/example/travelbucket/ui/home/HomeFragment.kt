package com.example.travelbucket.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelbucket.databinding.FragmentHomeBinding
import com.example.travelbucket.data.TravelPlace
import com.example.travelbucket.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TravelPlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        // Fetch travel places from server
        RetrofitClient.instance.getTravelPlaces().enqueue(object : Callback<List<TravelPlace>> {
            override fun onResponse(
                call: Call<List<TravelPlace>>,
                response: Response<List<TravelPlace>>
            ) {
                if (response.isSuccessful) {

                    val travelPlaces = response.body() ?: emptyList()

                    adapter = TravelPlaceAdapter(travelPlaces) { travelPlace ->
                        // Navigate to TravelPlaceDetailsFragment with SafeArgs
                        Log.d("HomeFragment", "Navigating to details with ID: ${travelPlace}")

                        if (travelPlace._id != null) {
                            val action = HomeFragmentDirections.actionNavHomeToNavTravelPlaceDetails(travelPlace._id)
                            Log.d("HomeFragment", "The city ${travelPlace.city} is clicked")
                            findNavController().navigate(action)

                        } else {
                            // Handle error: ID is null
                        }

                    }
                    binding.recyclerView.adapter = adapter
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<TravelPlace>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
    }
}

