package guru.springframework.bootstrap

import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent

abstract class AbstractBootstrap : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(p0: ContextRefreshedEvent?) = loadData()

    abstract fun loadData()
}
