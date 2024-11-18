package com.sparkfusion.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sparkfusion.data.entity.CourseEntity
import com.sparkfusion.data.utils.handleExceptionCode
import com.sparkfusion.data.utils.safePagingApiCall
import kotlinx.coroutines.CoroutineDispatcher

class CoursePagingSource(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher,
) : PagingSource<Int, CourseEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CourseEntity> {
        val page = params.key ?: 1

        return safePagingApiCall(dispatcher) {
            val response = apiService.getCourses(page = page)

            if (!response.isSuccessful) return@safePagingApiCall LoadResult.Error(
                handleExceptionCode(response.code())
            )

            val courses = response.body()?.courses ?: emptyList()
            LoadResult.Page(
                data = courses,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (courses.isEmpty()) null else page + 1
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CourseEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}





