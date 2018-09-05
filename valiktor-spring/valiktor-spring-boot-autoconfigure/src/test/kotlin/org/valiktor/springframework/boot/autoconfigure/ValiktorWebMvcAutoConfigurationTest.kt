package org.valiktor.springframework.boot.autoconfigure

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.FilteredClassLoader
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.DispatcherServlet
import org.valiktor.springframework.config.ValiktorConfiguration
import org.valiktor.springframework.web.controller.ValiktorExceptionHandler
import org.valiktor.springframework.web.controller.ValiktorJacksonExceptionHandler
import kotlin.test.Test

class ValiktorWebMvcAutoConfigurationTest {

    private val contextRunner = ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(
            ValiktorAutoConfiguration::class.java,
            ValiktorWebMvcAutoConfiguration::class.java
        ))

    @Test
    fun `should not create ValiktorExceptionHandler without ValiktorConfiguration`() {
        this.contextRunner
            .withClassLoader(FilteredClassLoader(ValiktorConfiguration::class.java))
            .run { context ->
                assertThat(context).doesNotHaveBean(ValiktorExceptionHandler::class.java)
            }
    }

    @Test
    fun `should not create ValiktorExceptionHandler without DispatcherServlet`() {
        this.contextRunner
            .withClassLoader(FilteredClassLoader(DispatcherServlet::class.java))
            .run { context ->
                assertThat(context).doesNotHaveBean(ValiktorExceptionHandler::class.java)
            }
    }

    @Test
    fun `should not create ValiktorJacksonExceptionHandler without MissingKotlinParameterException`() {
        this.contextRunner
            .withClassLoader(FilteredClassLoader(MissingKotlinParameterException::class.java))
            .run { context ->
                assertThat(context).hasSingleBean(ValiktorExceptionHandler::class.java)
                assertThat(context).doesNotHaveBean(ValiktorJacksonExceptionHandler::class.java)
            }
    }

    @Test
    fun `should create ValiktorExceptionHandler`() {
        this.contextRunner
            .run { context ->
                assertThat(context).hasSingleBean(ValiktorExceptionHandler::class.java)
            }
    }

    @Test
    fun `should create ValiktorExceptionHandler with custom bean`() {
        this.contextRunner
            .withUserConfiguration(
                ValiktorWebMvcCustomConfiguration::class.java
            )
            .run { context ->
                assertThat(context).hasSingleBean(ValiktorExceptionHandler::class.java)
                assertThat(context.getBean(ValiktorExceptionHandler::class.java)).isSameAs(
                    context.getBean(ValiktorWebMvcCustomConfiguration::class.java)
                        .valiktorExceptionHandler(context.getBean(ValiktorConfiguration::class.java)))
            }
    }

    @Test
    fun `should create ValiktorJacksonExceptionHandler`() {
        this.contextRunner
            .run { context ->
                assertThat(context).hasSingleBean(ValiktorJacksonExceptionHandler::class.java)
            }
    }

    @Test
    fun `should create ValiktorJacksonExceptionHandler with custom bean`() {
        this.contextRunner
            .withUserConfiguration(
                ValiktorWebMvcCustomConfiguration::class.java
            )
            .run { context ->
                assertThat(context).hasSingleBean(ValiktorJacksonExceptionHandler::class.java)
                assertThat(context.getBean(ValiktorJacksonExceptionHandler::class.java)).isSameAs(
                    context.getBean(ValiktorWebMvcCustomConfiguration::class.java)
                        .valiktorJacksonExceptionHandler(context.getBean(ValiktorExceptionHandler::class.java)))
            }
    }
}

@Configuration
private class ValiktorWebMvcCustomConfiguration {

    @Bean
    fun valiktorExceptionHandler(valiktorConfiguration: ValiktorConfiguration) =
        ValiktorExceptionHandler(valiktorConfiguration)

    @Bean
    fun valiktorJacksonExceptionHandler(valiktorExceptionHandler: ValiktorExceptionHandler) =
        ValiktorJacksonExceptionHandler(valiktorExceptionHandler)
}