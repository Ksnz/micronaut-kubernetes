package io.micronaut.kubernetes.client.operator.leaderelection

import io.kubernetes.client.extended.leaderelection.Lock
import io.kubernetes.client.extended.leaderelection.resourcelock.ConfigMapLock
import io.kubernetes.client.extended.leaderelection.resourcelock.EndpointsLock
import io.kubernetes.client.extended.leaderelection.resourcelock.LeaseLock
import io.micronaut.context.ApplicationContext
import spock.lang.Specification


class ResourceLockFactorySpec extends Specification {

    def "it resolves lease lock by default"() {
        when:
        ApplicationContext applicationContext = ApplicationContext.run([
                "micronaut.application.name": "app"
        ])

        then:
        applicationContext.getBean(Lock)
        applicationContext.getBean(Lock).class == LeaseLock.class
    }

    def "it resolves lease lock when configured"() {
        when:
        ApplicationContext applicationContext = ApplicationContext.run([
                "micronaut.application.name"                          : "app",
                "kubernetes.client.operator.leader-election.lock.resource-kind": "lease"
        ])

        then:
        applicationContext.getBean(Lock)
        applicationContext.getBean(Lock).class == LeaseLock.class
    }

    def "it resolves endpoints lock when configured"() {
        when:
        ApplicationContext applicationContext = ApplicationContext.run([
                "micronaut.application.name"                          : "app",
                "kubernetes.client.operator.leader-election.lock.resource-kind": "endpoints"
        ])

        then:
        applicationContext.getBean(Lock)
        applicationContext.getBean(Lock).class == EndpointsLock.class
    }


    def "it resolves configmap lock when configured"() {
        when:
        ApplicationContext applicationContext = ApplicationContext.run([
                "micronaut.application.name"                          : "app",
                "kubernetes.client.operator.leader-election.lock.resource-kind": "configmap"
        ])

        then:
        applicationContext.getBean(Lock)
        applicationContext.getBean(Lock).class == ConfigMapLock.class
    }

    def "it resolves owner reference when provided"() {

    }
}
