package xyz.khodok.khoblog.presentation.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import xyz.khodok.domain.model.Post
import xyz.khodok.khoblog.R
import xyz.khodok.khoblog.databinding.FragmentFeedBinding
import xyz.khodok.khoblog.presentation.feed.adapter.FeedAdapter
import xyz.khodok.khoblog.presentation.feed.viewmodel.FeedViewModel
import xyz.khodok.khoblog.util.navigate

@AndroidEntryPoint
class FeedFragment : Fragment(), FeedAdapter.OnFeedClickListener {

    private val feedViewModel: FeedViewModel by viewModels()

    private lateinit var binding: FragmentFeedBinding
    private lateinit var adapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        toolbar.visibility = View.INVISIBLE
        toolbar.setupWithNavController(navController, appBarConfiguration)
        ///Création du menu
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.visibility = View.VISIBLE

        ///Gestionnaire de clics
        toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener {
            if (it.itemId == R.id.settings_dest) {
                val action = FeedFragmentDirections.actionFeedFragmentToSettingsFragment()
                navController.navigate(action)
            }
            return@OnMenuItemClickListener true
        })

        setUpView()
        setupObserver()
        feedViewModel.fetchFeed()
    }


    private fun setUpView() {
        with(binding) {
            feedRecyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = FeedAdapter(mutableListOf(), this@FeedFragment)
            feedRecyclerView.adapter = adapter
        }
    }

    private fun setupObserver() {
        with(binding) {
            feedViewModel.feed.observe(viewLifecycleOwner, Observer { response ->
                response?.let {
                    contentLayout.visibility = View.VISIBLE
                    adapter.addToList(it.toMutableList())
                }
            })
            feedViewModel.isLoading.observe(viewLifecycleOwner, Observer { response ->
                response?.let {
                    loadingLayout.root.visibility = if (it) View.VISIBLE else View.GONE
                    if (it) {
                        contentLayout.visibility = View.GONE
                        errorLayout.root.visibility = View.GONE
                    }
                }
            })
            feedViewModel.isError.observe(viewLifecycleOwner, Observer { response ->
                response?.let {
                    errorLayout.root.visibility = if (it) View.VISIBLE else View.GONE
                    if (it) {
                        contentLayout.visibility = View.GONE
                        loadingLayout.root.visibility = View.GONE
                    }
                }
            })
        }
    }

    override fun onFeedClicked(feed: Post) {
        navigate(FeedFragmentDirections.actionFeedFragmentToDetailPostFragment(feed.slug))
    }

}
