package org.deadmoneypoker.dao

import org.deadmoneypoker.domain.Result

class ResultDao extends DeadMoneyDao{
    def insertResult(Result result) {
        def query = "insert into result (id, version, date_played, hitman_name, hits, player_name, points, rank, round_out, season_id, time_out, winnings, championship_ind) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        def keys = sql.executeInsert query, [getNextSequence(), 1, result.datePlayed.toTimestamp(), result.hitmanName, result.hits, result.playerName, result.points, result.rank, result.roundOut, result.seasonId.toInteger(), result.timeOut, result.winnings, result.championshipInd];

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
