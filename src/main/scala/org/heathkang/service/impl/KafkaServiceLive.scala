package org.heathkang.service.impl

import zio._
import zio.kafka.consumer._
import zio.kafka.producer._
import zio.kafka.serde._
import org.heathkang.domain.OperationalData
import org.heathkang.service.KafkaService

case class KafkaServiceLive(consumer: Consumer.Service, producer: Producer.Service[ProducerSettings, Serde.string, Serde.string]) extends KafkaService {
  def produceData(data: OperationalData): Task[Unit] = ???
  def consumeData(): Task[Unit] = ???
}

