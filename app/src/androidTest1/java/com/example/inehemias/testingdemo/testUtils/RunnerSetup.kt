package com.example.inehemias.testingdemo.testUtils
import org.junit.runner.notification.RunNotifier
import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.InitializationError
import timber.log.Timber.e

class RunnerSetup @Throws(InitializationError::class)
constructor(c: Class<*>) : BlockJUnit4ClassRunner(c) {
    override fun runChild(method: FrameworkMethod, notifier: RunNotifier) {
        val description = describeChild(method)
        var runMethod = false
        val methodTags = getMethodTags(method)
        if (method.getAnnotation(TestTags::class.java) != null) {
            UiTestSetup.tags.forEach { outerTag ->
                methodTags.forEach { innerTag ->
                    if (innerTag.equals(outerTag, ignoreCase = true)) {
                        runLeaf(methodBlock(method), description, notifier)
                        runMethod = true
                    }
                }
            }
            if (!runMethod) {
                with(notifier) {
                    fireTestIgnored(description
                                .also { e("Test Ignored ${ method.name }") })
                }
            }
        }
    }

    // get all tag names from test method
    private fun getMethodTags(method: FrameworkMethod): List<String> {
        val myTags = method.getAnnotation(TestTags::class.java).toString()
        val cleanTags = myTags.replace(".*\\[".toRegex(), "")
            .replace("]".toRegex(), "").replace("\\)".toRegex(), "")
            .replace(" ".toRegex(), "")
        return cleanTags.split(",".toRegex())
    }
}
