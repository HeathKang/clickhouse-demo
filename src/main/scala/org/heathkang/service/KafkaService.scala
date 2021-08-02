package org.heathkang.service

import org.heathkang.domain.OperationalData
import zio._
import org.heathkang.domain.OperationalData

trait KafkaService {
  def produceData(data: OperationalData): Task[Unit]
  def consumeData(): Task[Unit]
}