package com.example.genable

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

/**
 * Minimal MainActivity for Genable Template
 * White background, centered image, continuous background music
 */
class MainActivity : AppCompatActivity() {
    
    private lateinit var characterImage: ImageView
    private var mediaPlayer: MediaPlayer? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize views
        initializeViews()
        
        // Load character image
        loadCharacterImage()
        
        // Start background music
        startBackgroundMusic()
    }
    
    private fun initializeViews() {
        characterImage = findViewById(R.id.characterImage)
    }
    
    private fun loadCharacterImage() {
        characterImage.setImageResource(R.drawable.character_example)
    }
    
    private fun startBackgroundMusic() {
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.background_music_example)
            mediaPlayer?.let { player ->
                player.isLooping = true
                player.setVolume(1.0f, 1.0f)
                player.start()
            }
        } catch (e: Exception) {
            // Silent fail - continue without audio
        }
    }
    
    override fun onPause() {
        super.onPause()
        // Don't stop music when app goes to background
        // Music continues playing
    }
    
    override fun onResume() {
        super.onResume()
        // Ensure music is playing when app resumes
        if (mediaPlayer == null || !mediaPlayer!!.isPlaying) {
            startBackgroundMusic()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        stopMusic()
    }
    
    private fun stopMusic() {
        mediaPlayer?.let { player ->
            if (player.isPlaying) {
                player.stop()
            }
            player.release()
        }
        mediaPlayer = null
    }
}