package xyz.khodok.khoblog.presentation.main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import xyz.khodok.khoblog.BuildConfig
import xyz.khodok.khoblog.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
    }

    private fun setUpView() {
        val about =
            "This app has no real use. It's a way for its developer, Khodok, to get better at Android development, " +
                    "you can use it and report anything that you find odd." +
                    " I'd like to improve so any feedback is good." +
                    " If you're using it and you don't know me, that means I'm peaking, " +
                    "and you should make sure to try the website at https://blog.khodok.xyz since this is where the real work is done."
        val version =
            "Version: " + BuildConfig.VERSION_NAME + " | " + BuildConfig.VERSION_CODE.toString()

        binding.appAboutTextView.text = about
        binding.versionTextView.text = version
    }
}
