package org.deadmoneypoker.dao

import org.deadmoneypoker.domain.Result

class ResultDao extends DeadMoneyDao{
    def getResult(id) {
        def query = "select id, date_played, hitman_name, hits, player_name, points, rank, round_out, season_id, time_out, winnings from result where id = '" + id + "'"
        Result result = new Result()

        sql.eachRow query, {rowResult->
            result.id = rowResult.id
            result.datePlayed = rowResult.date_played
            result.hitmanName = rowResult.hitman_name
            result.hits = rowResult.hits
            result.playerName = rowResult.player_name
            result.points = rowResult.points
            result.rank = rowResult.rank
            result.roundOut = rowResult.round_out
            result.seasonId = rowResult.season_id
            result.timeOut = rowResult.time_out
            result.winnings = rowResult.winnings
        }

        result
    }

    def insertResult(Result result) {
        def query = "insert into result (id, version, date_played, hitman_name, hits, player_name, points, rank, round_out, season_id, time_out, winnings) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        def keys = sql.executeInsert query, [getNextSequence(), 1, result.datePlayed.toTimestamp(), result.hitmanName, result.hits, result.playerName, result.points, result.rank, result.roundOut, result.seasonId.toInteger(), result.timeOut, result.winnings];

        keys[0][0]
    }

    void deleteResult(long id) {
        def query = "delete from result where id = " + id
        sql.executeUpdate(query)
    }

    private def getNextSequence() {
        long seq;
        def query = ("select nextval('result_sequence')")

        sql.eachRow query, {rowResult->
            seq = rowResult.nextval
        }

        seq
    }
}
