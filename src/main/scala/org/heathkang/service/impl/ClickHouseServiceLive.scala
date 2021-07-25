package org.heathkang.service.impl

import zio._
import org.heathkang.service.ClickHouseService
import org.heathkang.domain.OperationalData
import com.crobox.clickhouse.ClickhouseClient
import com.crobox.clickhouse.internal.QuerySettings
import scala.concurrent.Future
import com.crobox.clickhouse.internal.QuerySettings.AllQueries

case class ClickHouseServiceLive(db: ClickhouseClient) extends ClickHouseService {

    def insertOperationalData(data: OperationalData): Task[Future[String]] =
        given QuerySettings = QuerySettings(QuerySettings.AllQueries)
        ZIO.effect(db.execute(
          s"INSERT INTO operational_data (*) VALUES " +
          s"('${data.name}', ${data.a1}, ${data.a2}, ${data.a3}, ${data.a4}, ${data.a5}, ${data.a6}, ${data.time});"
        ))
}

object ClickHouseServiceLive {
    val live: URLayer[Has[ClickhouseClient], Has[ClickHouseService]] = 
        (ClickHouseServiceLive(_)).toLayer
}

