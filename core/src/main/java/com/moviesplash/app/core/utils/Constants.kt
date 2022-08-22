package com.moviesplash.app.core.utils

object Constants {

    const val tableNameMovieHome = "movieHome"
    const val movieId = "id"
    const val movieTitle = "title"
    const val movieReleaseDate = "release_date"
    const val voteAverage = "vote_average"
    const val movieVoteCount = "vote_count"
    const val moviePosterPath = "poster_path"
    const val movieOverview = "overview"
    const val movieIsFavorite = "isFavorite"
    const val movieAction = "movie_action"

    const val API_KEY_PARAM = "api_key"
    const val MOVIE_BASE_URL = "https://api.themoviedb.org/3/"
    const val MOVIE_ORIGINAL_IMAGE = "https://image.tmdb.org/t/p/original"
    const val MOVIE_GET_PLAYING_NOW = "movie/now_playing"
    const val MOVIE_GET_UPCOMING = "movie/upcoming"
    const val MOVIE_GET_POPULAR = "movie/popular"
    const val MOVIE_GET_VIDEO = "movie/679/videos"
    const val DEFAULT_PAGE_PARAM = 1
    const val DEFAULT_LANGUAGE_PARAM = "en-US"
    const val KEY_LANGUAGE = "language"
    const val KEY_PAGE = "page"
    const val MOVIE_ID = "movie_id"
    const val ACTION_POPULAR = 2
    const val ACTION_UPCOMING = 1
    const val ACTION_PLAYING_NOW = 3
    const val GET_MOVIE_ID = "extra_movieId"

    const val GRID_SPAN_COUNT = 2
    const val GRID_SPAN = 3

    const val NOTIFICATION_CHANNEL_ID = "notify-channel"
    const val NOTIF_UNIQUE_WORK: String = "NOTIF_UNIQUE_WORK"

    //    Search Movie
    const val MOVIE_SEARCH_PARAM = "search/movie"
    const val SEARCH_INCLUDE_ADULT = "include_adult"
    const val SEARCH_QUERY = "query"
    const val MOVIE_ADULT_PARAM = false

    const val videoTableName = "video_entity"
    const val MOVIE_VIDEO_KEY = "keyVideo"
    const val video_name = "name"
}