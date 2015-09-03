package org.deadmoneypoker.dao

import spock.lang.Specification

class ResultDaoSpec extends Specification {
    ResultDao resultsDao = new ResultDao()
    SeasonDao seasonsDao = new SeasonDao()

    def setup() {
    }

    def cleanup() {
        resultsDao.closeDb()
        seasonsDao.closeDb()
    }

    void "test something"() {
        expect: "canary is alive"
        true == true
    }
}
