package com.app.rectonote.database

import androidx.room.*

@Dao
interface DraftTrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun newDraftTrack(track: DraftTrackEntity): Long

    @Query("SELECT * FROM draft_tracks WHERE project_id = :projectId  ORDER BY date_modified DESC")
    suspend fun loadTracksFromProject(projectId: Int): MutableList<DraftTrackEntity>

    @Query("SELECT tracks_name FROM draft_tracks WHERE project_id =:projectId")
    suspend fun loadTrackNames(projectId: Int): List<String>

    @Update
    suspend fun changeData(track: DraftTrackEntity)

    @Delete
    suspend fun deleteTrack(track: DraftTrackEntity)
}