package org.heathkang.service

import org.heathkang.domain.OperationalData
import zio._
import org.apache.kafka.clients.producer.RecordMetadata

trait KafkaService {
  def produceData(data: OperationalData): Task[RecordMetadata]
  def consumeData(): Task[Unit]
}

object KafkaService {
  def produceData(data: OperationalData): RIO[Has[KafkaService], RecordMetadata] = 
    ZIO.serviceWith[KafkaService](_.produceData(data))
}