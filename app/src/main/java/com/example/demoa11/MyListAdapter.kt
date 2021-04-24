package com.example.demoa11

import android.app.Activity
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MyListAdapter(private val context: Activity, private val title: MutableList<String>) :
    ArrayAdapter<String>(context, R.layout.custom_list, title) {

    var mediaPlayer: MediaPlayer? = null

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val subtitleText = rowView.findViewById(R.id.description) as TextView
        imageView.setOnClickListener {
            play1(Uri.parse(title[position]))
        }
        titleText.text = title[position]

        return rowView
    }

    fun play1(audioFileUri: Uri?) {
        var myUri: Uri = audioFileUri!!
        if (mediaPlayer != null) {
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
            mediaPlayer = null
        }
        mediaPlayer = MediaPlayer();
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer!!.setDataSource(context, myUri);
        mediaPlayer!!.prepare();
        mediaPlayer!!.start();
    }
}