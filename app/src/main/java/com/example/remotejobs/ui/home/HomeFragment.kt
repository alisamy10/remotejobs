package com.example.remotejobs.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.remotejobs.R
import com.example.remotejobs.common.Resource
import com.example.remotejobs.common.gone
import com.example.remotejobs.common.show
import com.example.remotejobs.data.model.ResponseItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.getViewModel

class HomeFragment : Fragment(R.layout.fragment_home), HomeAdapter.Interaction,
    SearchView.OnQueryTextListener {
    lateinit var viewModel: HomeViewModel
    private val homeAdapter by lazy { HomeAdapter(this) }
    private lateinit var navController: NavController
    private lateinit var responseList: MutableList<ResponseItem>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setHasOptionsMenu(true)
        responseList = mutableListOf()

        setupRecyclerView()
        observeToNewsLiveData()
        observeToErrorLiveData(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = getViewModel()
        viewModel.getHomeNews()
    }
    private fun observeToErrorLiveData(view: View) {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            if(it){
                viewModel.error.postValue(false)
                Snackbar.make(
                    view,
                    ("No Data Saved "),
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction(("retry")) {
                        viewModel.getHomeNews()
                    }
                    .show()
            }
        })
    }
    private fun setupRecyclerView() {
        swipeRefresh.apply {
            setOnRefreshListener {
                observeToNewsLiveData()
                 viewModel.getHomeNews()
            }
        }
        newsRecycler.apply {
            adapter = homeAdapter
        }
    }
    private fun observeToNewsLiveData() {
        viewModel.getJobs().observe(viewLifecycleOwner, Observer {
            Log.e("ali", "state${it}");
            when (it) {
                is Resource.Error -> {
                    ProgressBar.gone()
                }
                is Resource.Loading -> ProgressBar.show()
                is Resource.Success -> {
                    if (it.data != null) {
                        ProgressBar.gone()
                        swipeRefresh.isRefreshing = false
                        homeAdapter.differ.submitList(it.data)
                        responseList.addAll(it.data)  // add the call from api to list in memory to search
                    }
                }
            }
        })
    }
    override fun onItemSelected(position: Int, item: ResponseItem) {
        val action = HomeFragmentDirections.actionHomeFragmentToHomeDetailsFragment(item)
        findNavController().navigate(action)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        onQueryTextChange(query)
        return true
    }
    override fun onQueryTextChange(newText: String?): Boolean {
        homeAdapter.differ.submitList(searchQuery(newText, responseList))
        return true
    }
}