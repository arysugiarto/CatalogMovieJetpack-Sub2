package com.arysugiarto.catalogmoviejetpack.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
//import com.arysugiarto.catalogmoviejetpack.ui.crew.CrewFragment
import com.arysugiarto.catalogmoviejetpack.ui.descfragment.DescFragment

class DetailPagerAdapter (viewPagerManager: FragmentManager) : FragmentStatePagerAdapter(viewPagerManager){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> DescFragment()
            else -> DescFragment()
        }
    }

    override fun getCount(): Int = 1

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Description"
            else -> "Crew"
        }
    }
}