/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.domain.usecases

import arrow.core.Either
import es.marcrdz.domain.domain.ErrorDO

interface UseCase<in T, out S> {

    suspend operator fun invoke(param: T? = null): Either<ErrorDO, S>
}