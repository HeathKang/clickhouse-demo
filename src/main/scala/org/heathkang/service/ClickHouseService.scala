package org.heathkang.service
import zio._
import org.heathkang.domain.OperationalData
import java.io.IOException
import scala.concurrent.Future

trait ClickHouseService {
    def insertOperationalData(data: OperationalData): Task[Future[String]]
}

object ClickHouseService {
    def insertOperationalData(data: OperationalData):RIO[Has[ClickHouseService],Future[String]] =
        ZIO.serviceWith[ClickHouseService](_.insertOperationalData(data))
}