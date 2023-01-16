/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.base

import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.presentation.domain.Data

data class BaseStateHolder<D: Data>(
    val isLoading: Boolean = false,
    val error: ErrorDO? = null,
    val data: D
)