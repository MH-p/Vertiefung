package com.example.vertiefungqrvisitenkartenapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(activity: FragmentActivity, private val tabCount: Int) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> FragmentQrScanner()
            1 -> FragmentQrGenerator()
            else -> FragmentQrScanner()
        }
    }
}