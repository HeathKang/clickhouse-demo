import zio._
import zio.random._
import zio.clock._
import zio.console._
import zio.duration.durationInt
import org.heathkang.domain.OperationalData
import java.util.concurrent.TimeUnit
import java.io.IOException
import org.heathkang.service.SourceService
import org.heathkang.service.impl.SourceServiceLive

object MyApp extends zio.App {

    def run(args: List[String]) =
        val action: ZIO[Has[SourceService] with Console with Clock, IOException, Unit] = 
            for {
                operationalData <- SourceService.generateOperationalData
                _ <- putStrLn(operationalData.toString) *> sleep(1.seconds)
            } yield ()
        
        val policy = Schedule.recurs(10)
        action.provideCustomLayer(SourceServiceLive.layer).repeat(policy).exitCode

}