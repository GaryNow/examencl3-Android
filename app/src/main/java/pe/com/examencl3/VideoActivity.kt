package pe.com.examencl3

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView

class VideoActivity : AppCompatActivity() {

    private lateinit var player: SimpleExoPlayer
    private lateinit var playerView: PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        playerView = findViewById(R.id.playerView)
        val videoUrl = intent.getStringExtra("VIDEO_URL")

        if (!videoUrl.isNullOrEmpty()) {
            initializePlayer(videoUrl)
        } else {
            // Manejar el caso donde la URL del video es nula o vacía
            Toast.makeText(this, "URL del video no válida", Toast.LENGTH_SHORT).show()
            finish() // Cerrar la actividad si no hay URL válida
        }
    }

    private fun initializePlayer(videoUrl: String) {
        val trackSelector = DefaultTrackSelector(this)
        player = SimpleExoPlayer.Builder(this).setTrackSelector(trackSelector).build()
        playerView.player = player

        val mediaItem = MediaItem.fromUri(videoUrl)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}