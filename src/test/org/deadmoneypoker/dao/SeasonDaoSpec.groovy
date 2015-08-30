package org.deadmoneypoker.dao

import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.doDomainClassUnitTestMixin} for usage instructions
 */
class SeasonDaoSpec extends Specification {
    SeasonDao seasonsDao

    def setup() {
        seasonsDao = new SeasonDao()
    }

    def cleanup() {
        seasonsDao.closeDb()
    }

    void "test something"() {
        expect: "canary is alive"
        true == true
    }

    void "test getSeasons returns correct season"() {
        final def name = "Season 1"
        def season = seasonsDao.getSeason(name)

        expect: "Season 1 is returned"
        name == season.name
        2007 == season.startYear
        null != season.id
    }
}
