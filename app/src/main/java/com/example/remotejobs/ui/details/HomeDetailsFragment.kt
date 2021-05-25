package com.example.remotejobs.ui.details

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.navArgs
import com.example.remotejobs.R
import com.example.remotejobs.common.showToast
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.fragment_home_details.*
import org.koin.core.KoinComponent
import org.koin.core.get
import androidx.navigation.fragment.findNavController

import com.example.remotejobs.common.gone


class HomeDetailsFragment : Fragment(R.layout.fragment_home_details), KoinComponent {
    private val args: HomeDetailsFragmentArgs by navArgs()
    private val glide: RequestManager = get()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
        shareBtn.setOnClickListener {
            try {
                val i = Intent(Intent.ACTION_SEND)
                i.type = "text/plan"
                i.putExtra(Intent.EXTRA_SUBJECT, args.JOBSITEM.title)
                val body: String =
                    args.JOBSITEM.title.toString() + "\n" + args.JOBSITEM.url + "\n" + "Share from the Jobs App" + "\n"
                i.putExtra(Intent.EXTRA_TEXT, body)
                startActivity(Intent.createChooser(i, "Share with :"))
            } catch (e: Exception) {
                showToast("Sorry, \nCannot be share")
            }
        }

        if(args.JOBSITEM.companyUrl==null)
            openWebSite.gone()

        openWebSite.setOnClickListener {
            val action
               = args.JOBSITEM.companyUrl?.let { it1 ->
                HomeDetailsFragmentDirections.actionHomeDetailsFragmentToWebViewFragment(
                    it1
                )
            }
            action?.let { it1 -> findNavController().navigate(it1) }
        }
    }

    private fun bindData() {
        glide.load(args.JOBSITEM.companyLogo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(articleImage)
        val formattedDescriptionString = getString(R.string.job_description, args.JOBSITEM.description)
        descriptionTxt.text =
            HtmlCompat.fromHtml(formattedDescriptionString, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}