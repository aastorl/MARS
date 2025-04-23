package com.example.marsphotos.fake.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

// TestDispatcherRule is a JUnit rule to help you control how coroutines run during tests.
// It specifically manages the main Coroutine Dispatcher.
class TestDispatcherRule(
    // You can provide a specific TestDispatcher, like UnconfinedTestDispatcher by default.
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
): TestWatcher() {

    // This runs before each test method.
    override fun starting(description: Description) {
        // Sets the main dispatcher to our test dispatcher.
        // This lets us control coroutine execution on the 'main' thread in tests.
        Dispatchers.setMain(testDispatcher)
    }

    // This runs after each test method.
    override fun finished(description: Description) {
        // Resets the main dispatcher back to its original state.
        Dispatchers.resetMain()
    }
}
// This rule helps simulate how coroutines behave in different test scenarios,
// allowing you to test code that uses Dispatchers.Main.