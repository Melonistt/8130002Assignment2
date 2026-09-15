package com.example.s8130002Application2.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s8130002Application2.MainActivity
import com.example.s8130002Application2.R
import com.example.s8130002Application2.adapters.CoursesAdapter
import com.example.s8130002Application2.adapters.SkillsAdapter
import com.example.s8130002Application2.databinding.ActivityProfileBinding
import com.example.s8130002Application2.models.SkillItem
import com.example.s8130002Application2.models.TeachingItem
import com.example.s8130002Application2.models.UserProfile
import com.google.android.material.chip.Chip

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editProfileButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Load user profile data
        loadUserProfile()
        setupRecyclerViews()
        setupAchievements()
    }

    private fun loadUserProfile() {
        val userProfile = getUserProfileData()

        binding.apply {
            // Load profile image
            //Picasso.get()                               TBD
            //    .load(userProfile.profileImageUrl)
            //    .placeholder(R.drawable.placeholder_profile)
            //    .into(profileImage)

            // Set basic info
            nameTextView.text = userProfile.name
            bioTextView.text = userProfile.bio
            ratingBar.rating = userProfile.rating
            reviewsCount.text = "${userProfile.reviews}"
            followersCount.text = if (userProfile.followers >= 1000) {
                "${userProfile.followers / 1000}K"
            } else {
                "${userProfile.followers}"
            }
            followingCount.text = "${userProfile.following}"

            // Set click listeners
            editProfileButton.setOnClickListener {
                // Handle edit profile
            }
        }
    }


    private fun setupRecyclerViews() {
        val userProfile = getUserProfileData()

        // Skills RecyclerView
        binding.skillsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ProfileActivity)
            adapter = SkillsAdapter(userProfile.skills)
        }

        // Teaching RecyclerView
        binding.teachingRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ProfileActivity)
            adapter = CoursesAdapter(userProfile.teaching)
        }

        // Learning RecyclerView
        binding.learningRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ProfileActivity)
            adapter = CoursesAdapter(userProfile.learning)
        }
    }

    private fun setupAchievements() {
        val userProfile = getUserProfileData()

        binding.achievementsChipGroup.apply {
            userProfile.achievements.forEach { achievement ->
                val chip = Chip(this@ProfileActivity).apply {
                    text = achievement
                    isCheckable = false
                    setChipBackgroundColorResource(R.color.light_orange)
                    setTextColor(getColor(R.color.primary_orange))
                }
                addView(chip)
            }
        }
    }

    private fun getUserProfileData(): UserProfile {
        // Sample data - replace with actual data from your backend/database
        return UserProfile(
            name = "Yuna Matsuda",
            rating = 4.5f,
            reviews = 142,
            followers = 1200,
            following = 38,
            bio = "Music composer and software developer passionate about sharing knowledge.",
            profileImageUrl = "https://example.com/profile.jpg",
            skills = listOf(
                SkillItem("Composition", 90, getColor(R.color.primary_orange)),
                SkillItem("Music Theory", 85, getColor(R.color.skill_blue)),
                SkillItem("Sound Design", 80, getColor(R.color.skill_yellow)),
                SkillItem("Kotlin", 88, getColor(R.color.skill_purple))
            ),
            achievements = listOf(
                "Top Tutor",
                "5-Star Reviews",
                "Helpful"
            ),
            teaching = listOf(
                TeachingItem(
                    id = 1,
                    title = "Music Composition Fundamentals",
                    category = "Music",
                    rating = 4.8f,
                    imageUrl = R.drawable.placeholder_profile
                ),
                TeachingItem(
                    id = 2,
                    title = "Introduction to Kotlin Programming",
                    category = "Programming",
                    rating = 4.6f,
                    imageUrl = R.drawable.placeholder_profile
                )
            ),
            learning = listOf(
                TeachingItem(
                    id = 3,
                    title = "Advanced Web Development",
                    category = "Web Dev",
                    rating = 4.7f,
                    imageUrl = R.drawable.placeholder_profile
                ),
                TeachingItem(
                    id = 4,
                    title = "Digital Marketing Masterclass",
                    category = "Marketing",
                    rating = 4.5f,
                    imageUrl = R.drawable.placeholder_profile
                )
            )
        )
    }
}