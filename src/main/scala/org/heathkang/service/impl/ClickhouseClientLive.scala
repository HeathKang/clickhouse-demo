package org.heathkang.service.impl

import zio._
import com.crobox.clickhouse.ClickhouseClient
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

object ClickhouseClientLive {
    val live: TaskLayer[Has[ClickhouseClient]] = 
        ZLayer.fromZIO(for {
            config <- getConfig
            // queryDatabase <- ZIO.succeed("default")
        } yield ClickhouseClient(Some(config)))
    
    def getConfig: Task[Config] =
        ZIO.attempt(ConfigFactory.load())
}