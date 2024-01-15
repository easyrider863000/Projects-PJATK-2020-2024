package com.example.mp1


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.ListFragment


class MainActivity : AppCompatActivity() {

    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, listFragment, listFragment.javaClass.name)
            .commit()
    }
}
