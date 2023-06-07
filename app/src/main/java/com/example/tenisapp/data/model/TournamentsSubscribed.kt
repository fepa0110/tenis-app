package com.example.tenisapp.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.tenisapp.data.model.Tournament
import com.example.tenisapp.data.model.TournamentUser
import com.example.tenisapp.data.model.User

data class TournamentsSubcribed(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "tournamentId",
        associateBy = Junction(TournamentUser::class)
    )
    val tournaments: List<Tournament>
)