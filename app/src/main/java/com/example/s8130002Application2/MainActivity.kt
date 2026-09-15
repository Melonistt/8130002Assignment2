package com.example.s8130002Application2

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s8130002Application2.adapters.CoursesAdapter
import com.example.s8130002Application2.models.Instructor
import com.example.s8130002Application2.models.TeachingItem
import com.google.android.material.chip.Chip
import com.example.s8130002Application2.databinding.ActivityMainBinding
import com.example.s8130002Application2.ui.profile.ProfileActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigate to Profile Activity
        binding.exploreBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Navigate to Login
        binding.settingsBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Set up category chips
        setupCategoryChips()

        // Set up trending workshops
        setupTrendingWorkshops()

        // Set up popular instructors
        setupInstructors()
    }



    private fun setupCategoryChips() {
        val categoryContainer = findViewById<LinearLayout>(R.id.categoryContainer)
        val categories = listOf("All", "Design", "Illustration", "Animation")

        categories.forEach { category ->
            val chip = Chip(this).apply {
                text = category
                isCheckable = true
                if (category == "All") isChecked = true
            }
            categoryContainer.addView(chip)
        }
    }

    private fun setupTrendingWorkshops() {
        val recycler = findViewById<RecyclerView>(R.id.trendingWorkshopsRecycler)
        val workshops = listOf(
            TeachingItem(
                1,
                "Digital Illustration Fundamentals",
                "Design",
                4.8f,
                R.drawable.placeholder_course
            ),
            TeachingItem(2, "Web Design Masterclass", "Design", 4.7f, R.drawable.placeholder_course),
            TeachingItem(3, "Motion Graphics with After Effects", "Animation", 4.9f, R.drawable.placeholder_course),
            TeachingItem(4, "Type & Lettering for Creatives", "Design", 4.6f, R.drawable.placeholder_course)
        )

        recycler.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = CoursesAdapter(workshops)
        }
    }

    private fun setupInstructors() {
        val recycler = findViewById<RecyclerView>(R.id.instructorsRecycler)
        val instructors = listOf(
            Instructor(1, "Yuna M.", 1200, R.drawable.placeholder_profile),
            Instructor(2, "Alex R.", 950, R.drawable.placeholder_profile),
            Instructor(3, "Sara D.", 1450, R.drawable.placeholder_profile),
            Instructor(4, "James L.", 820, R.drawable.placeholder_profile)
        )

        recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter
        }
    }
}
