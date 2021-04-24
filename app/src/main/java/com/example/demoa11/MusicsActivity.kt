
package com.example.demoa11

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_musics.*

class MusicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musics)

        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION
        )

        var cursor = managedQuery(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )

        val songs: MutableList<String> = ArrayList()
        val songsPath: MutableList<String> = ArrayList()
        var song: String? = null
        var count = 0
        while (cursor.moveToNext()) {
            songs.add(
                cursor.getString(0)
                    .toString() + "||" + cursor.getString(1) + "||" + cursor.getString(2) + "||" + cursor.getString(
                    3
                ) + "||" + cursor.getString(4) + "||" + cursor.getString(5)
            )
            songsPath.add(cursor.getString(3))
            Log.e("file details 1", " name =${cursor.getString(3)} ")

        }
//        if (songs.isNotEmpty()) {
//            for (i in 0 until songs.size) {
//                //here you will get list of file name and file path that present in your device
//                Log.e("file details 1", " name =${songs[i]} ")
//            }
//        }
        val myListAdapter = MyListAdapter(this,songsPath)
        listView.adapter = myListAdapter

        listView.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            Toast.makeText(this, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
        }
    }

    fun play1(audioFileUri: Uri?) {
        var myUri: Uri = audioFileUri!!
        var mediaPlayer: MediaPlayer = MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(applicationContext, myUri);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }
}