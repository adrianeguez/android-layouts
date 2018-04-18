package com.layouts.adrian.layouts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actividad_layouts.*

class ActividadLayouts : AppCompatActivity() {
    var mThumbIds = arrayOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_layouts)
        mThumbIds = arrayOf<Int>(
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli,
                R.drawable.poli, R.drawable.poli)
        gridview.adapter = ImageAdapter(this)
        gridview.onItemClickListener =
                AdapterView.OnItemClickListener { parent, v, position, id ->
                    Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
                }
    }

    class ImageAdapter(val mContext: ActividadLayouts) : BaseAdapter() {

        override fun getCount(): Int = mContext.mThumbIds.size

        override fun getItem(position: Int): Any? = null

        override fun getItemId(position: Int): Long = 0L

        // create a new ImageView for each item referenced by the Adapter
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val imageView: ImageView
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = ImageView(mContext)
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.layoutParams = ViewGroup.LayoutParams(35, 35)
                imageView.setPadding(8, 8, 8, 8)
            } else {
                imageView = convertView as ImageView
            }

            imageView.setImageResource(mContext.mThumbIds[position])
            return imageView
        }
    }
}
