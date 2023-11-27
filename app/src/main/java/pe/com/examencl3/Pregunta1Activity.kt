package pe.com.examencl3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pe.com.examencl3.ProyectoREST.CustomAdapterVideo
import pe.com.examencl3.ProyectoREST.RetrofitHelper
import pe.com.examencl3.ProyectoREST.VideoAPI

class Pregunta1Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapterVideo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta1)

        recyclerView = findViewById(R.id.rv_list_videos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapterVideo(emptyList()) { videoUrl ->
            openVideoActivity(videoUrl)
        }
        recyclerView.adapter = adapter

        val videoApi = RetrofitHelper.getRetroFitInstance().create(VideoAPI::class.java);

        GlobalScope.launch {
            val response = videoApi.getBooks()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val categories = body.categories
                    val videos = categories.flatMap { it.videos }
                    runOnUiThread {
                        adapter = CustomAdapterVideo(videos) { videoUrl ->
                            openVideoActivity(videoUrl)
                        }
                        recyclerView.adapter = adapter
                    }
                }
            }
        }

    }

    private fun openVideoActivity(videoUrl: String) {
        val intent = Intent(this, VideoActivity::class.java)
        intent.putExtra("VIDEO_URL", videoUrl)
        startActivity(intent)
    }
}