package com.ubicar.ubicar.services.predictor

import com.ubicar.ubicar.dtos.GoogleLoginUserDTO
import com.ubicar.ubicar.dtos.UserCreationDTO
import com.ubicar.ubicar.dtos.UserDTO
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.User
import java.util.Optional

interface PredictorService {

  fun requestPrediction(property: Property): String
}
