package com.example.learnsource

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.learnsource.databinding.FragmentAddItemBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AddItemFragment : Fragment() {
    val args:AddItemFragmentArgs by navArgs()
    private  val mainViewModel:MainViewModel by lazy{
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    private val SubListItems:LinkedList<String> = LinkedList()

    private var _binding:FragmentAddItemBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAddItemBinding.inflate(inflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding!!.buttonAddChip.setOnClickListener {

            var textToSet=_binding!!.EditChipGroup.text.toString()
            if(isValid(textToSet)) {
              CreateChip(textToSet)
                _binding!!.EditChipGroup.text =Editable.Factory.getInstance().newEditable(" ")
            }
        }
        _binding!!.CreateButton.setOnClickListener {
            val text=_binding!!.titleEditText.text.toString()
            if(isValid(text)){
                val item=ItemMainList(text,0,MakeListOfItems())

                if(args.item.title!=null && args.item.podlista!=null){
                    args.item.podlista!!.add(item)
                    mainViewModel.updateMainItem(args.item)

                }else{
                    mainViewModel.MainItemInsert(item)
                }


                findNavController().navigate(R.id.action_addItemFragment_to_mainListFragment)

            }else{
                Toast.makeText(requireContext(),"Invalid Title",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun CreateChip(textToSet: String) {
        val chip = Chip(requireContext())
        chip.isChecked = true
        chip.text =textToSet
        chip.closeIcon=ContextCompat.getDrawable(requireContext(),R.drawable.close)
        chip.isCloseIconVisible=true
        chip.setOnCloseIconClickListener {
            _binding!!.ChipGroup.removeView(it)
            SubListItems.remove(textToSet)
        }

        _binding!!.ChipGroup.addView(chip)
        SubListItems.add(textToSet)
    }

    private fun MakeListOfItems(): LinkedList<ItemMainList>? {
        var list=LinkedList<ItemMainList>()
        for (element in SubListItems){
            val item=ItemMainList(element,0,null)
            list.add(item)
        }

        return list

    }

    private fun isValid(text: String): Boolean {
        return !text.isEmpty()
    }
}