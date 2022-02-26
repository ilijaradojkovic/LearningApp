package com.example.learnsource

import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.learnsource.databinding.FragmentMainList2Binding
import java.lang.Exception

class BindingAdapter {
    companion object{

        @BindingAdapter("onClickedNavigation")
        @JvmStatic
        fun onClickedNavigation(constraintLayout: ConstraintLayout,itemMainList: ItemMainList){
            constraintLayout.setOnClickListener {
                var action:NavDirections?=null

                     action=MainListFragmentDirections.actionMainListFragmentSelf(itemMainList)
                    if(itemMainList.podlista!=null)
                    constraintLayout.findNavController().navigate(action!!)



            }
        }
        @BindingAdapter("updateItem")
        @JvmStatic
        fun updateItem(imageView: ImageView,itemMainList: ItemMainList)
        {
            imageView.setOnClickListener {
                var action:NavDirections?=null

                    action=MainListFragmentDirections.actionMainListFragmentToUpdateFragment(itemMainList)

                        imageView.findNavController().navigate(action!!)


                }




            }


        }

}