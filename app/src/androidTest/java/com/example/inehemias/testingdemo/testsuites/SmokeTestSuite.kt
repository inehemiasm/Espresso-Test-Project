package com.example.inehemias.testingdemo.testsuites

import com.example.inehemias.testingdemo.tests.DemoTests
import com.example.inehemias.testingdemo.tests.SecondDemoTests
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(DemoTests::class, SecondDemoTests::class)
class SmokeTestSuite