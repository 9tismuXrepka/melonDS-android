package me.magnum.melonds.ui.cheats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.magnum.melonds.databinding.FragmentCheatsSubscreenBinding

abstract class SubScreenFragment : Fragment() {
    protected val viewModel: CheatsViewModel by activityViewModels()
    private lateinit var binding: FragmentCheatsSubscreenBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCheatsSubscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listItems.apply {
            val listLayoutManager = LinearLayoutManager(context)
            layoutManager = listLayoutManager
            addItemDecoration(DividerItemDecoration(context, listLayoutManager.orientation))
            adapter = getSubScreenAdapter()
        }
        binding.listItems.adapter?.notifyDataSetChanged()

        if (binding.listItems.adapter?.itemCount == 0) {
            getNoContentText()?.let {
                binding.textNoContent.apply {
                    isVisible = true
                    text = it
                }
            }
        }

        (requireActivity() as AppCompatActivity).supportActionBar?.title = getScreenName()
    }

    abstract fun getSubScreenAdapter(): RecyclerView.Adapter<*>

    abstract fun getScreenName(): String?

    open fun getNoContentText(): String? = null
}