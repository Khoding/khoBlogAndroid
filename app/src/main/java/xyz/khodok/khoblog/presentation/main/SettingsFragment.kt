package xyz.khodok.khoblog.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import xyz.khodok.khoblog.BuildConfig
import xyz.khodok.khoblog.R
import xyz.khodok.khoblog.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
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
        toolbar.visibility = View.VISIBLE

        setUpView()
    }

    private fun setUpView() {
        with(binding) {
            val version =
                "Version: " + BuildConfig.VERSION_NAME + " | " + BuildConfig.VERSION_CODE.toString()
            binding.versionTextView.text = version
        }
    }

    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }
}