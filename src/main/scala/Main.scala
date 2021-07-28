import zio._
import zio.Random._
import zio.Clock._
import zio.Console._
import zio.Duration._
import scala.concurrent.Future
import org.heathkang.domain.OperationalData
import java.util.concurrent.TimeUnit
import org.heathkang.service.{SourceService, ClickHouseService}
import org.heathkang.service.impl.{SourceServiceLive, ClickHouseServiceLive, ClickhouseClientLive}
import org.heathkang.service.ClickHouseService

object MyApp extends zio.App {

    def run(args: List[String]) =
        val action: ZIO[Has[Console] with Has[SourceService] with Has[ClickHouseService] with Has[Clock]  , Throwable, Unit] = 
            for {
                operationalData <- SourceService.generateOperationalData
                _ <- printLine(operationalData.toString) *> sleep(10.milliseconds)
                future <- ClickHouseService.insertOperationalData(operationalData)
            } yield ()
        
        val policy = Schedule.forever

        action
            .repeat(policy)
            .injectCustom(
                SourceServiceLive.live, 
                ClickHouseServiceLive.live,
                ClickhouseClientLive.live)
            .exitCode

}