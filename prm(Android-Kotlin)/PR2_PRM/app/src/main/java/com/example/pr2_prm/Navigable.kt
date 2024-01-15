package com.example.pr2_prm

interface Navigable {
    enum class Destination{
        List, Add
    }
    fun navigate(to: Destination)
}