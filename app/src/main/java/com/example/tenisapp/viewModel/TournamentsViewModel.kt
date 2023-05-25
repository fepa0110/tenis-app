/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tenisapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tenisapp.data.repository.TournamentsRepository
import com.example.tenisapp.data.model.Tournament
import com.example.tenisapp.data.repository.TournamentRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/** View Model to retrieve all items in the Room database. */
class TournamentsViewModel(tournamentsRepository: TournamentRepositoryInterface) : ViewModel() {


    val tournaments: Flow<List<Tournament>> = tournamentsRepository.getAllTournamentsStream();
    /**
     * Holds home ui state. The list of items are retrieved from [TournamentsRepository] and mapped to
     * [TournamentsUiState]
     */
/*     val tournamentsUiState: StateFlow<TournamentsUiState> =
            tournamentsRepository
                    .getAllTournamentsStream()
                    .map { TournamentsUiState(it) }
                    .stateIn(
                            scope = viewModelScope,
                            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                            initialValue = TournamentsUiState()
                    )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    } */
}


/** Ui State for TournamentsScreen */
// data class TournamentsUiState(val tournamentsList: List<Tournament> = listOf())