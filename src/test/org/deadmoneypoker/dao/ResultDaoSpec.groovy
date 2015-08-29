package org.deadmoneypoker.dao

import org.deadmoneypoker.domain.Result
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

    void "inserts result record"() {
        final seasonName = "Season 1"
        final today = new Date()
        def season = seasonsDao.getSeason(seasonName)

        def result = new Result(
                seasonId: season.id,
                datePlayed: today,
                playerName: "Me",
                rank: 2,
                points: 42,
                winnings: new BigDecimal("100.55"),
                timeOut: "beer thirty",
                roundOut: "10",
                hits: 5,
                hitmanName: "You")

        def id = resultsDao.insertResult(result)
        def actualResult = resultsDao.getResult(id)

        expect: "actual to be equal to expected"

        id == actualResult.id
        today.format('yyyyMMdd') == actualResult.datePlayed.format('yyyyMMdd')
        "You" == actualResult.hitmanName
        5 == actualResult.hits
        "Me" == actualResult.playerName
        42 == actualResult.points
        2 == actualResult.rank
        "10" == actualResult.roundOut
        season.id == actualResult.seasonId
        "beer thirty" == actualResult.timeOut
        new BigDecimal("100.55") == actualResult.winnings

        cleanup:
        resultsDao.deleteResult(id)
    }
}
