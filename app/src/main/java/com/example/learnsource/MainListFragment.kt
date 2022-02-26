package com.example.learnsource

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnsource.databinding.FragmentMainList2Binding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainListFragment : Fragment(),SearchView.OnQueryTextListener {
    private val args:MainListFragmentArgs by navArgs()
    private  val madapter: MainRecyclerViewAdapter by lazy{
        MainRecyclerViewAdapter()
    }
    private var mlayoutManager:LinearLayoutManager ?=null
    private var _biding:FragmentMainList2Binding?=null
    private val mainViewModel:MainViewModel by lazy{
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _biding= FragmentMainList2Binding.inflate(inflater)
        setupRecyclerView()
        setHasOptionsMenu(true)
        return _biding!!.root
    }

    private fun setupRecyclerView() {
        mlayoutManager= LinearLayoutManager(requireContext())
        _biding!!.recyclerView.adapter=madapter
        _biding!!.recyclerView.layoutManager=mlayoutManager
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if(args.item==null) {
            mainViewModel.allDataItemMain.observe(viewLifecycleOwner, {
                val mutabl = mutableListOf(it)

                madapter.lista.submitList(mutabl[0])
            })
        }else{
                madapter.lista.submitList(args.item!!.podlista)
        }

        _biding!!.floatingActionButton.setOnClickListener {
            val action=MainListFragmentDirections.actionMainListFragmentToAddItemFragment(ItemMainList(null,0,null))

            findNavController().navigate(action)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val lista=LinkedList<ItemMainList>()
       madapter.lista.submitList( madapter.lista.currentList.filter{
            it.title!!.toLowerCase().contains(query!!)
        }
        )
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_search,menu)
        val serchview=menu.findItem(R.id.Search).actionView as SearchView
        serchview.setOnQueryTextListener(this)
        serchview.isSubmitButtonEnabled=true
        super.onCreateOptionsMenu(menu, inflater)
    }
}