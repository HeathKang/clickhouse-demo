package org.heathkang.service

import zio._
import org.heathkang.domain.OperationalData


trait SourceService {
    def generateOperationalData: UIO[OperationalData]
}

object SourceService {
  def generateOperationalData: URIO[Has[SourceService], OperationalData] = 
    ZIO.serviceWith[SourceService](_.generateOperationalData)
}
