package com.emirkaya.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.emirkaya.movieapp.data.model.actor.ActorItem
import com.emirkaya.movieapp.data.model.moviemodel.MovieItem
import com.emirkaya.movieapp.data.network.ApiService

class SearchActorsPagingSource(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, ActorItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ActorItem> {
        val page = params.key ?: 1
        return try {
            val response = apiService.searchActors(query, page)
            val actors = response.body()?.actorItems ?: emptyList()
            LoadResult.Page(
                data = actors,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (actors.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ActorItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}