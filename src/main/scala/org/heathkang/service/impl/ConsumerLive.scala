package org.heathkang.service.impl

import zio._
import zio.clock.Clock
import zio.blocking._
import zio.kafka.consumer._

object ConsumerLive {
  val live: RLayer[Clock with Blocking, Has[Consumer.Service]] = 
    ZLayer.fromManaged(
        Consumer.make(
            ConsumerSettings(
                List("localhost:9082")
            ).withGroupId("group")
        )
    )
}