import zio._
import zio.random._
import zio.clock._
import zio.console._
import zio.duration.durationInt
import scala.concurrent.Future
import org.heathkang.domain.OperationalData
import java.util.concurrent.TimeUnit
import org.heathkang.service.{SourceService, ClickHouseService}
import org.heathkang.service.impl.{SourceServiceLive, ClickHouseServiceLive, ClickhouseClientLive}
import org.heathkang.service.ClickHouseService

object MyApp extends zio.App {

    def run(args: List[String]) =
        val action: ZIO[Console with Clock with Has[SourceService] with Has[ClickHouseService]  , Throwable, Unit] = 
            for {
                operationalData <- SourceService.generateOperationalData
                _ <- putStrLn(operationalData.toString) *> sleep(10.milliseconds)
                future <- ClickHouseService.insertOperationalData(operationalData)
                    // _ <- Fiber.fromFuture[String](future)
                // _ <- fiber.join
                // // data  <- fiber.join

                // _ <- ZIO.fromFuture({
                //     implicit ec => future.flatMap({
                //         result => Future(println(result))
                // })
                // })
            } yield ()
        
        val policy = Schedule.forever
        action.provideCustomLayer(SourceServiceLive.live ++ (ClickhouseClientLive.live >>> ClickHouseServiceLive.live)).repeat(policy).exitCode

}