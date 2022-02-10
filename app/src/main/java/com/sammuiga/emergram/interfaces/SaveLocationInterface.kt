package com.sammuiga.emergram.interfaces

import com.sammuiga.emergram.SavedPlaceModel

interface SaveLocationInterface {

    fun onLocationClick(savedPlaceModel: SavedPlaceModel)
}