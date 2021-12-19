package com.ubicar.ubicar.services.predictor

import com.ubicar.ubicar.entities.Property

interface PredictorService {

  fun requestPrediction(property: Property): String
  fun sendGeodata(coordinates: String): String
}
