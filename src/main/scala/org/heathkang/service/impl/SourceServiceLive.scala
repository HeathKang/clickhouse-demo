package org.heathkang.service.impl

import zio._
import zio.console._
import zio.random._
import zio.clock._
import org.heathkang.service.SourceService
import org.heathkang.domain.OperationalData
import java.util.concurrent.TimeUnit


case class SourceServiceLive(
    console: Console.Service,
    random:  Random.Service,
    clock: Clock.Service) extends SourceService {
    
  override def generateOperationalData: UIO[OperationalData] =
    for {
            index <- random.nextIntBetween(0,1)
            name <- ZIO.succeed(List("1000", "1001")(index))
            a1   <- random.nextDoubleBetween(20.0, 30.0)
            a2   <- random.nextDoubleBetween(20.0, 30.0)
            a3   <- random.nextDoubleBetween(20.0, 30.0)
            a4   <- random.nextDoubleBetween(20.0, 30.0)
            a5   <- random.nextDoubleBetween(20.0, 30.0)
            a6   <- random.nextDoubleBetween(20.0, 30.0)
            time <- clock.currentTime(TimeUnit.MILLISECONDS)
    } yield OperationalData(name,a1,a2,a3,a4,a5,a6,time)
}

object SourceServiceLive {
  val layer: URLayer[Has[Console.Service] with Has[Random.Service] with Has[Clock.Service], Has[SourceService]] =
    (SourceServiceLive(_, _, _)).toLayer   
}
