package org.valiktor.i18n

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.valiktor.ConstraintViolation
import org.valiktor.DefaultConstraintViolation
import org.valiktor.constraints.NotEquals
import java.util.Locale
import java.util.Locale.ENGLISH
import java.util.ResourceBundle.getBundle

private object I18nConstraintViolationFixture {

    fun createConstraintViolation(): ConstraintViolation =
            DefaultConstraintViolation(property = "name", value = "Test", constraint = NotEquals("Test"))
}

class I18nConstraintViolationTest {

    @Test
    fun `should convert to I18nConstraintViolation with default params`() {
        val i18nConstraintViolation = I18nConstraintViolationFixture.createConstraintViolation()
                .toI18n()

        assertAll(
                { assertEquals(i18nConstraintViolation.property, "name") },
                { assertEquals(i18nConstraintViolation.value, "Test") },
                { assertEquals(i18nConstraintViolation.constraint, NotEquals("Test")) },
                { assertEquals(i18nConstraintViolation.message, "Must not be equal to Test") }
        )
    }

    @Test
    fun `should convert to I18nConstraintViolation with custom locale`() {
        val i18nConstraintViolation = I18nConstraintViolationFixture.createConstraintViolation()
                .toI18n(locale = Locale("pt", "BR"))

        assertAll(
                { assertEquals(i18nConstraintViolation.property, "name") },
                { assertEquals(i18nConstraintViolation.value, "Test") },
                { assertEquals(i18nConstraintViolation.constraint, NotEquals("Test")) },
                { assertEquals(i18nConstraintViolation.message, "Não deve ser igual a Test") }
        )
    }

    @Test
    fun `should convert to I18nConstraintViolation with custom baseName`() {
        val i18nConstraintViolation = I18nConstraintViolationFixture.createConstraintViolation()
                .toI18n(baseName = "testMessages")

        assertAll(
                { assertEquals(i18nConstraintViolation.property, "name") },
                { assertEquals(i18nConstraintViolation.value, "Test") },
                { assertEquals(i18nConstraintViolation.constraint, NotEquals("Test")) },
                { assertEquals(i18nConstraintViolation.message, "Should not be equal to Test") }
        )
    }

    @Test
    fun `should convert to I18nConstraintViolation with custom key`() {
        val i18nConstraintViolation = I18nConstraintViolationFixture.createConstraintViolation()
                .toI18n(key = "org.valiktor.constraints.Equals.message")

        assertAll(
                { assertEquals(i18nConstraintViolation.property, "name") },
                { assertEquals(i18nConstraintViolation.value, "Test") },
                { assertEquals(i18nConstraintViolation.constraint, NotEquals("Test")) },
                { assertEquals(i18nConstraintViolation.message, "Must be equal to Test") }
        )
    }

    @Test
    fun `should convert to I18nConstraintViolation with custom locale and baseName`() {
        val i18nConstraintViolation = I18nConstraintViolationFixture.createConstraintViolation()
                .toI18n(locale = ENGLISH, baseName = "testMessages")

        assertAll(
                { assertEquals(i18nConstraintViolation.property, "name") },
                { assertEquals(i18nConstraintViolation.value, "Test") },
                { assertEquals(i18nConstraintViolation.constraint, NotEquals("Test")) },
                { assertEquals(i18nConstraintViolation.message, "Cannot be equal to Test") }
        )
    }

    @Test
    fun `should convert to I18nConstraintViolation with custom locale and key`() {
        val i18nConstraintViolation = I18nConstraintViolationFixture.createConstraintViolation()
                .toI18n(locale = ENGLISH, key = "org.valiktor.constraints.Equals.message")

        assertAll(
                { assertEquals(i18nConstraintViolation.property, "name") },
                { assertEquals(i18nConstraintViolation.value, "Test") },
                { assertEquals(i18nConstraintViolation.constraint, NotEquals("Test")) },
                { assertEquals(i18nConstraintViolation.message, "Must be equal to Test") }
        )
    }

    @Test
    fun `should convert to I18nConstraintViolation with custom baseName and key`() {
        val i18nConstraintViolation = I18nConstraintViolationFixture.createConstraintViolation()
                .toI18n(baseName = "testMessages", key = "org.valiktor.constraints.NotEquals.message.custom")

        assertAll(
                { assertEquals(i18nConstraintViolation.property, "name") },
                { assertEquals(i18nConstraintViolation.value, "Test") },
                { assertEquals(i18nConstraintViolation.constraint, NotEquals("Test")) },
                { assertEquals(i18nConstraintViolation.message, "Cannot be equal to Test") }
        )
    }

    @Test
    fun `should convert to I18nConstraintViolation with custom locale, baseName and key`() {
        val i18nConstraintViolation = I18nConstraintViolationFixture.createConstraintViolation()
                .toI18n(locale = ENGLISH, baseName = "testMessages", key = "org.valiktor.constraints.NotEquals.message.custom")

        assertAll(
                { assertEquals(i18nConstraintViolation.property, "name") },
                { assertEquals(i18nConstraintViolation.value, "Test") },
                { assertEquals(i18nConstraintViolation.constraint, NotEquals("Test")) },
                { assertEquals(i18nConstraintViolation.message, "Should not be equal to Test") }
        )
    }

    @Test
    fun `should convert to I18nConstraintViolation with custom ResourceBundle`() {
        val i18nConstraintViolation = I18nConstraintViolationFixture.createConstraintViolation()
                .toI18n(resourceBundle = getBundle("testMessages", Locale("")))

        assertAll(
                { assertEquals(i18nConstraintViolation.property, "name") },
                { assertEquals(i18nConstraintViolation.value, "Test") },
                { assertEquals(i18nConstraintViolation.constraint, NotEquals("Test")) },
                { assertEquals(i18nConstraintViolation.message, "Should not be equal to Test") }
        )
    }

    @Test
    fun `should convert to I18nConstraintViolation with custom ResourceBundle and key`() {
        val i18nConstraintViolation = I18nConstraintViolationFixture.createConstraintViolation()
                .toI18n(resourceBundle = getBundle("testMessages", ENGLISH), key = "org.valiktor.constraints.NotEquals.message.custom")

        assertAll(
                { assertEquals(i18nConstraintViolation.property, "name") },
                { assertEquals(i18nConstraintViolation.value, "Test") },
                { assertEquals(i18nConstraintViolation.constraint, NotEquals("Test")) },
                { assertEquals(i18nConstraintViolation.message, "Should not be equal to Test") }
        )
    }
}