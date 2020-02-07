package com.arysugiarto.catalogmoviejetpack.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.core.Is
import org.junit.Assert

class RecyclerViewAssertion (private val recyclerViewAssertion: Int) : ViewAssertion{
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if(noViewFoundException != null){
            throw noViewFoundException
        }

        val recyclerViewAssertion = view as RecyclerView
        val adapter = recyclerViewAssertion.adapter
        Assert.assertNotNull(adapter)
        ViewMatchers.assertThat(adapter!!.itemCount, Is.`is`(this.recyclerViewAssertion))
    }
}