package es.marcrdz.presentation.handlers.main

import es.marcrdz.presentation.base.Report
import es.marcrdz.presentation.domain.PresentationReference


sealed class MainReport : Report() {

    class PokemonReferencesFetched(val references: List<PresentationReference.Pokemon>) : MainReport()
    class PokemonReferencesSelected(val reference: PresentationReference.Pokemon) : MainReport()
}