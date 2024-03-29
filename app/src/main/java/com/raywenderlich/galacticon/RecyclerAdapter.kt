package com.raywenderlich.galacticon

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*


class RecyclerAdapter(private val photos: ArrayList<Photo>) :
    RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.PhotoHolder {
//here you inflate the view fromt its layout and pass it in to a PhotoHolder
        // the parent.inflate() will execute the new ViewGroup.inflate() extension function to inflate the layout
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return PhotoHolder(inflatedView)

    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: RecyclerAdapter.PhotoHolder, position: Int) {

        val itemPhoto = photos[position]
        holder.bindPhoto(itemPhoto)
    }


    class PhotoHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {


        private var view: View = v
        private var photo: Photo? = null;


        init {
            v.setOnClickListener(this)
        }

        companion object {

            private val PHOTO_KEY = "PHOTO"
        }

        override fun onClick(itemView: View?) {
            //Log.d("RecyclerView", "CLICK!")
            val context = itemView?.context
            val showPhotoIntent = Intent (context, PhotoActivity::class.java)
            showPhotoIntent.putExtra(PHOTO_KEY, photo)
            context?.startActivity(showPhotoIntent)
        }


        fun bindPhoto (photo: Photo) {

            this.photo = photo
            Picasso.with(view.context).load(photo.url).into(view.itemImage)
            view.itemDate.text = photo.humanDate
            view.itemDescription.text = photo.explanation

        }

    }
}