import zio._
import zio.Random._
import zio.Clock._
import zio.Console._
import zio.blocking._
import zio.Duration._
import scala.concurrent.Future
import org.heathkang.domain.OperationalData
import java.util.concurrent.TimeUnit
import org.heathkang.service.{SourceService, ClickHouseService, KafkaService}
import org.heathkang.service.impl.{SourceServiceLive, ClickHouseServiceLive, ClickhouseClientLive, KafkaServiceLive, ConsumerLive, ProducerLive}
import shapeless.record

object MyApp extends zio.App {

    def run(args: List[String]) =
        val action: ZIO[Has[Console] with Has[SourceService] with Has[ClickHouseService] with Has[Clock] with Has[KafkaService] , Throwable, Unit] = 
            for {
                operationalData <- SourceService.generateOperationalData
                record <- KafkaService.produceData(operationalData)
                _ <- printLine(operationalData.toString) *> sleep(10.milliseconds)
                future <- ClickHouseService.insertOperationalData(operationalData)
            } yield ()
        
        val policy = Schedule.forever

        action
            .repeat(policy)
            .injectCustom(
                SourceServiceLive.live, 
                ClickHouseServiceLive.live,
                ClickhouseClientLive.live,
                Clock.live,
                Blocking.live,
                ConsumerLive.live,
                ProducerLive.live,
                KafkaServiceLive.live)
            .exitCode

}