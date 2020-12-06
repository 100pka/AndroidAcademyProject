package com.stopkaaaa.androidacademyproject.domain

import com.stopkaaaa.androidacademyproject.R
import com.stopkaaaa.androidacademyproject.data.models.Movie

class MoviesDataSource {
    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                "Dolittle",
                "Adventure, Comedy, Family",
                13,
                101,
                87,
                2,
                false,
                R.drawable.pic_movie_image_in_details_dolittle,
                R.drawable.pic_movie_image_in_list_dolittle,
                "A physician who can talk to animals embarks on an adventure to find a legendary island with a young apprentice and a crew of strange pets.",
                ActorsDataSource().getDolittleActors()
            ),
            Movie(
                "Underwater",
                "Action, Horror, Sci-Fi",
                18,
                95,
                113,
                3,
                false,
                R.drawable.pic_movie_image_in_details_underwater,
                R.drawable.pic_movie_image_in_list_underwater,
                "A crew of oceanic researchers working for a deep sea drilling company try to get to safety after a mysterious earthquake devastates their deepwater research and drilling facility located at the bottom of the Mariana Trench.",
                ActorsDataSource().getUnderwaterActors()
            ),
            Movie(
                "The Call Of The Wild",
                "Adventure, Drama, Family",
                13,
                119,
                321,
                3,
                false,
                R.drawable.pic_movie_image_in_details_the_call_of_the_wild,
                R.drawable.pic_movie_image_in_list_the_call_of_the_wild,
                "A sled dog struggles for survival in the wilds of the Yukon.",
                ActorsDataSource().getTheCallOfTheWildActors()
            ),
            Movie(
                "Last Christmas",
                "Comedy, Drama, Romance",
                13,
                121,
                208,
                3,
                false,
                R.drawable.pic_movie_image_in_details_last_christmas,
                R.drawable.pic_movie_image_in_list_last_christmas,
                "Kate is a young woman subscribed to bad decisions. Working as an elf in a year round Christmas store is not good for the wannabe singer. However, she meets Tom there. Her life takes a new turn. For Kate, it seems too good to be true.",
                ActorsDataSource().getLastChristmasActors()
            ),
            Movie(
                "The Invisible Guest",
                "Crime, Drama, Mystery",
                16,
                106,
                173,
                4,
                true,
                R.drawable.pic_movie_image_in_details_the_invisible_guest,
                R.drawable.pic_movie_image_in_list_the_invisible_guest,
                "A successful entrepreneur accused of murder and a witness preparation expert have less than three hours to come up with an impregnable defense.",
                ActorsDataSource().getInvisibleGuestActors()
            ),
            Movie(
                "Fantasy Island",
                "Action, Adventure, Fantasy",
                16,
                109,
                181,
                2,
                true,
                R.drawable.pic_movie_image_in_details_fantasy_island,
                R.drawable.pic_movie_image_in_list_fantasy_island,
                "When the owner and operator of a luxurious island invites a collection of guests to live out their most elaborate fantasies in relative seclusion, chaos quickly descends.",
                ActorsDataSource().getFantasyIslandActors()
            )
        )
    }
}







