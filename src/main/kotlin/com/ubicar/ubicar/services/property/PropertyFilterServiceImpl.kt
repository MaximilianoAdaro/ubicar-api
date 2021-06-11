package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.repositories.property.filter.PropertyFilterOperationRepository
import com.ubicar.ubicar.utils.SessionUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PropertyFilterServiceImpl(
    private val sessionUtils: SessionUtils,
    private val propertyFilterRepository: PropertyFilterOperationRepository
) : PropertyFilterService {

    override fun filterEvaluationsPaginated(filter: PropertyFilterDto, pageRequest: PageRequest,
        params: PropertyLazyTableDto, orderList: List<String>
    ): Page<Property> {
        val loggedUser: User = sessionUtils.getTokenUserInformation()
        return propertyFilterRepository.getFilteredProperties(filter, loggedUser, pageRequest, params, orderList)
    }

}