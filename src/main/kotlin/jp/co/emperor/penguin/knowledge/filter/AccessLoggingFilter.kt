package jp.co.emperor.penguin.knowledge.filter

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AccessLoggingFilter : OncePerRequestFilter() {

    companion object {
        private const val STATUS_CODE = "status_code"
        private const val REQUEST_URL = "request_url"
        private const val LATENCY = "latency"
        private const val UNIQUE_ID = "uuid"
        private  val logger = LoggerFactory.getLogger(AccessLoggingFilter::class.java)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            // レイテンシ計測
            val start = System.currentTimeMillis()

            MDC.put(UNIQUE_ID, UUID.randomUUID().toString())
            MDC.put(REQUEST_URL, request.servletPath)

            filterChain.doFilter(request, response)

            MDC.put(STATUS_CODE, response.status.toString())
            MDC.put(LATENCY, (System.currentTimeMillis() - start).toString())
            logger.info("")
        } finally {
            // 確実に Context から削除する
            MDC.remove(STATUS_CODE)
            MDC.remove(REQUEST_URL)
            MDC.remove(LATENCY)
            MDC.remove(UNIQUE_ID)
        }
    }
}