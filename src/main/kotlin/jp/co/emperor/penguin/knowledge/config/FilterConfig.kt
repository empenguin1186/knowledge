package jp.co.emperor.penguin.knowledge.config

import jp.co.emperor.penguin.knowledge.filter.AccessLoggingFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {

    @Bean
    fun accessLoggingFilter() = FilterRegistrationBean(AccessLoggingFilter()).also {
        it.addUrlPatterns("*")
        it.order = Integer.MIN_VALUE
    }
}