package com.ubicar.ubicar.repositories.property.filter.predicate

import com.vividsolutions.jts.geom.Geometry
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl
import org.hibernate.query.criteria.internal.ParameterRegistry
import org.hibernate.query.criteria.internal.Renderable
import org.hibernate.query.criteria.internal.compile.RenderingContext
import org.hibernate.query.criteria.internal.expression.LiteralExpression
import org.hibernate.query.criteria.internal.predicate.AbstractSimplePredicate
import java.io.Serializable
import javax.persistence.criteria.Expression
import javax.persistence.criteria.Path

class ContainsPredicate(
  criteriaBuilder: CriteriaBuilderImpl,
  private val matchExpression: Expression<Geometry>,
  private val area: Expression<Geometry>
) :
  AbstractSimplePredicate(criteriaBuilder), Serializable {

  constructor(criteriaBuilder: CriteriaBuilderImpl, matchExpression: Geometry, area: Path<Geometry>) : this(
    criteriaBuilder, LiteralExpression<Geometry>(criteriaBuilder, matchExpression), area
  )

  override fun registerParameters(registry: ParameterRegistry) {
    // Nothing to register
  }

  override fun render(isNegated: Boolean, renderingContext: RenderingContext): String {
    return " st_contains(" +
      (matchExpression as Renderable).render(renderingContext) +
      ", " +
      (area as Renderable).render(renderingContext) +
      ") = true"
  }
}
