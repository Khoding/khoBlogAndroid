package xyz.khodok.khoblog.presentation.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import xyz.khodok.khoblog.databinding.FragmentDetailPostBinding
import xyz.khodok.khoblog.presentation.detail.viewmodel.DetailPostViewModel

@AndroidEntryPoint
class DetailPostFragment : Fragment() {

    private val detailPostViewModel: DetailPostViewModel by viewModels()
    private val args: DetailPostFragmentArgs by navArgs()

    private lateinit var binding: FragmentDetailPostBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setupObserver()
        detailPostViewModel.fetchDetailPost(args.postSlug)
    }

    private fun setUpView() {
    }

    private fun setupObserver() {
        with(binding) {
            detailPostViewModel.post.observe(viewLifecycleOwner, { response ->
                response?.let { postDetail ->
                    contentLayout.visibility = View.VISIBLE
                    binding.titleTextView.text = postDetail.title
                    binding.bodyTextView.text =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Html.fromHtml(postDetail.formattedMarkdown, Html.FROM_HTML_MODE_COMPACT)
                        } else {
                            Html.fromHtml(postDetail.formattedMarkdown)
                        }
                }
            })
            detailPostViewModel.isLoading.observe(viewLifecycleOwner, { response ->
                response?.let {
                    loadingLayout.root.visibility = if (it) View.VISIBLE else View.GONE
                    if (it) {
                        contentLayout.visibility = View.GONE
                        errorLayout.root.visibility = View.GONE
                    }
                }
            })
            detailPostViewModel.isError.observe(viewLifecycleOwner, { response ->
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

}
