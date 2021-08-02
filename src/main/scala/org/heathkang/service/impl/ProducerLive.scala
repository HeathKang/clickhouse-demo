package org.heathkang.service.impl

import zio._
import zio.kafka.producer._
import zio.kafka.serde._
import zio.clock.Clock
import zio.blocking.Blocking

object ProducerLive {
  val live: RLayer[Clock with Blocking, Has[Producer.Service[Any, String, String]]] = 
    ZLayer.fromManaged(
        Producer.make(
            ProducerSettings(List("localhost:9092")),
            Serde.string,
            Serde.string
        )
    )
}