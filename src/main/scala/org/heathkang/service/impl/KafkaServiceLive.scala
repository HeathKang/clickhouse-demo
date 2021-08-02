package org.heathkang.service.impl

import zio._
import zio.kafka.consumer._
import zio.kafka.producer._
import zio.kafka.serde._
import org.heathkang.domain.OperationalData
import org.heathkang.service.KafkaService
import org.apache.kafka.clients.producer.RecordMetadata

case class KafkaServiceLive(consumer: Consumer.Service, producer: Producer.Service[Any, String, String]) extends KafkaService {
  override def produceData(data: OperationalData): Task[RecordMetadata] = 
    val result = producer.produce(
      "operational_data",
      data.name,
      data.toString
    )
    result

  override def consumeData(): Task[Unit] = ???
}

object KafkaServiceLive {
  val live: URLayer[Has[Consumer.Service] with Has[Producer.Service[Any, String, String]], Has[KafkaService]] = {
    (for {
      consumer <- ZIO.service[Consumer.Service]
      producer <- ZIO.service[Producer.Service[Any, String, String]]
    } yield KafkaServiceLive(consumer, producer)).toLayer
  }
}

