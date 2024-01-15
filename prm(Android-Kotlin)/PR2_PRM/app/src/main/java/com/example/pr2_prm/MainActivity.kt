package com.example.pr2_prm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pr2_prm.fragments.AddFragment
import com.example.pr2_prm.fragments.EditFragment
import com.example.pr2_prm.fragments.ListFragment
import com.example.pr2_prm.fragments.SampleFragment
import com.example.pr2_prm.notification.Notifications

class MainActivity : AppCompatActivity(), Navigable{

    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.maincontainer, listFragment, listFragment.javaClass.name)
            .commit()

        Notifications.createChannel(this)
    }

    override fun navigate(to: Navigable.Destination){
        supportFragmentManager.beginTransaction().apply {
            when (to){
                Navigable.Destination.List -> replace(R.id.maincontainer, listFragment, listFragment.javaClass.name)

                Navigable.Destination.Add -> {
                    replace(R.id.maincontainer, AddFragment(), AddFragment::class.java.name)
                    addToBackStack(AddFragment::class.java.name)
                }
            }.commit()
        }
    }

    fun navigateToreview(fragment: SampleFragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.maincontainer, fragment, SampleFragment::class.java.name)
            addToBackStack(fragment::class.java.name)
        }.commit()
    }

    fun navigateToEdit(fragment: EditFragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.maincontainer, fragment, EditFragment::class.java.name)
            addToBackStack(fragment::class.java.name)
        }.commit()
    }

}