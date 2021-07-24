import zio._
import zio.random._
import zio.clock._
import zio.console._
import zio.duration.durationInt
import org.heathkang.domain.OperationalData
import java.util.concurrent.TimeUnit
import java.io.IOException

object MyApp extends zio.App {

    def run(args: List[String]) =
        val action: ZIO[Console with Clock with Random, IOException, Unit] = 
            for {
                operationalData <- randomOperationalData
                _ <- putStrLn(operationalData.toString) *> sleep(1.seconds)
            } yield ()
        
        val policy = Schedule.recurs(10)
        action.repeat(policy).exitCode

        

    def randomOperationalData: URIO[Random with Clock, OperationalData] = 
        for {
            index <- nextIntBetween(0,1)
            name <- ZIO.succeed(List("1000", "1001")(index))
            a1   <- nextDoubleBetween(20.0, 30.0)
            a2   <- nextDoubleBetween(20.0, 30.0)
            a3   <- nextDoubleBetween(20.0, 30.0)
            a4   <- nextDoubleBetween(20.0, 30.0)
            a5   <- nextDoubleBetween(20.0, 30.0)
            a6   <- nextDoubleBetween(20.0, 30.0)
            time <- currentTime(TimeUnit.MILLISECONDS)
        } yield OperationalData(name,a1,a2,a3,a4,a5,a6,time)

}