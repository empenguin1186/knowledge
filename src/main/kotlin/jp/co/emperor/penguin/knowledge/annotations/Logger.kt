package jp.co.emperor.penguin.knowledge.annotations

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val Any.logger: Logger
    get() {
        return LoggerFactory.getLogger(this.javaClass)
    }