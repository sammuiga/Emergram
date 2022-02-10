package com.sammuiga.emergram.interfaces

import com.sammuiga.emergram.googlePlaceModel.GooglePlaceModel

interface NearLocationInterface {

    fun onSaveClick(googlePlaceModel: GooglePlaceModel)

    fun onDirectionClick(googlePlaceModel: GooglePlaceModel)
}