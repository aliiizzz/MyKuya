package ir.aliiz.base

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RxRule(val scheduler: Scheduler): TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                RxAndroidPlugins.initMainThreadScheduler { scheduler }

                try {
                    base?.evaluate()
                } finally {
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}