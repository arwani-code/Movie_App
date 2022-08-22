package com.moviesplash.app.core

import com.moviesplash.app.core.domain.model.MovieHome

object DataDummy {

    fun generateDummyMovie(): List<MovieHome>{
        val movieHome = ArrayList<MovieHome>()

        for(i in 0..10){
            val movie = MovieHome(
                id = i,
                movieId = 20,
                title = "Thor and Love",
                "2-5-2022",
                23.2,
                293,
                "sdkmsdksmdks",
                "lorem ipsum",
                false,
                i
            )
            movieHome.add(movie)
        }

        return movieHome
    }

}