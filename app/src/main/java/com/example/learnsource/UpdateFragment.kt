package com.example.learnsource

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.learnsource.databinding.FragmentAddItemBinding
import com.google.android.material.chip.Chip
import java.util.*


class UpdateFragment : Fragment() {
    val args:UpdateFragmentArgs by navArgs()
    private  val mainViewModel:MainViewModel by lazy{
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    private val SubListItems: LinkedList<String> = LinkedList()

    private var _binding: FragmentAddItemBinding?=null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAddItemBinding.inflate(inflater)
        SetupViews()
        return _binding!!.root
    }

    private fun SetupViews() {

        if(args.itemForUpdate.podlista!=null) {
            args.itemForUpdate.podlista!!.forEach {

                CreateChip(it.title!!, true)
            }
        }
        _binding!!.titleEditText.text=Editable.Factory.getInstance().newEditable(args.itemForUpdate.title)

        _binding!!.buttonAddChip.setOnClickListener {
            val text=_binding!!.EditChipGroup.text.toString()
            CreateChip(text,false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding!!.CreateButton.setOnClickListener {
            mainViewModel.updateMainItem(args.itemForUpdate)
            findNavController().navigate(R.id.action_updateFragment_to_mainListFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun CreateChip(textToSet: String, flag: Boolean) {
        val item=ItemMainList(textToSet,0,null)
        val chip = Chip(requireContext())
        chip.isChecked = true
        chip.text =textToSet
        chip.closeIcon= ContextCompat.getDrawable(requireContext(),R.drawable.close)
        chip.isCloseIconVisible=true
        chip.setOnCloseIconClickListener {
            _binding!!.ChipGroup.removeView(it)
            args.itemForUpdate.podlista!!.remove(item)
        }

        _binding!!.ChipGroup.addView(chip)
        if(!flag)
        args.itemForUpdate.podlista!!.add(item)
    }

}