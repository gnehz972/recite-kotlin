package com.recite.zz.kotlin.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.recite.zz.kotlin.base.BaseActivity
import com.recite.zz.kotlin.repository.data.DailySentence
import com.recite.zz.kotlin.repository.data.Word
import com.recite.zz.kotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var wordViewModel: WordViewModel
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        getComponent().inject(this)

//        addTv.setOnClickListener {
////            count++
////            val word = Word("eng_"+count,"ddd","dd","","","")
////            wordViewModel.addSingleWord(word)
//
//
//            wordViewModel.fetchDailySentence()
//                    .subscribe{
//                        textTv.text = it.content
//                    }
//        }

//        viewPager.adapter = TabPagerAdapter(supportFragmentManager)
//        tabLayout.setupWithViewPager(viewPager)
        initTab()
    }


    private fun initTab(){
        tabhost.setup(this, supportFragmentManager, R.id.content)
        tabhost.tabWidget.setBackgroundColor(resources.getColor(android.R.color.white))
        tabhost.tabWidget.dividerDrawable = null

        addTabHost("1",HomeFragment::class.java ,resources.getDrawable(R.drawable.tab_dict),
                "", null)

        addTabHost("2",BookFragment::class.java ,resources.getDrawable(R.drawable.tab_dict),
                "", null)

        addTabHost("3",RememberFragment::class.java ,resources.getDrawable(R.drawable.tab_dict),
                "", null)

        addTabHost("4",SettingFragment::class.java ,resources.getDrawable(R.drawable.tab_dict),
                "", null)
    }


    private fun addTabHost(tabId: String, clz: Class<*>, drawable: Drawable, text: String, bundle: Bundle?) {
        val tabSpec = tabhost.newTabSpec(tabId)
                .setIndicator(getItemView(drawable, text))
        tabhost.addTab(tabSpec, clz, bundle)
    }

    private fun getItemView(drawable: Drawable, text: String): View {
        val view = layoutInflater.inflate(R.layout.tab_item, null)
        val tabTv : TextView = view.findViewById(R.id.tab_text)
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        tabTv.setCompoundDrawables(null, drawable, null, null)
        tabTv.text = text
        return view
    }
}
