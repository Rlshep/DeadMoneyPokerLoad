package org.deadmoneypoker.load

import spock.lang.Specification

class LoadResultsFromCsvSpec extends Specification {
    LoadResultsFromCsv loadResultsFromCsv

    def setup() {
        loadResultsFromCsv = new LoadResultsFromCsv()
    }

    def cleanup() {

    }

    void "test something"() {
        expect: "canary is alive"
        true == true
    }

    void "getDateFromFileNameDate gets correct date from file"() {
        final String fileName = 'bunch of gobbedlygook/results_20150824.csv'
        final Date expectedDate = Date.parse(LoadResultsFromCsv.DATE_FORMAT, '20150824')

        Date acutalDate = loadResultsFromCsv.getDateFromFilePath(fileName)

        expect:
        expectedDate == acutalDate
    }
}
