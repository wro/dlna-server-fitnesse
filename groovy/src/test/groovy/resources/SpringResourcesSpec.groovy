package resources

import config.ConfigurationHolder
import groovyx.net.http.RESTClient
import org.springframework.context.support.ClassPathXmlApplicationContext
import rest.GroovyRestClient
import spock.lang.Specification

class SpringResourcesSpec extends Specification {

    def context = new ClassPathXmlApplicationContext("classpath:/beans.xml")

    def "config holder"() {
        expect:
        configHolder
    }

    private ConfigurationHolder getConfigHolder() {
        context.getBean(ConfigurationHolder)
    }

    def "rest client"() {
        expect:
        restClient.uri.toString() == configHolder.config.rest.url
    }

    private RESTClient getRestClient() {
        context.getBean(RESTClient)
    }

    def "groovy rest client"() {
        expect:
        groovyRestClient.restClient == restClient
    }

    private GroovyRestClient getGroovyRestClient() {
        context.getBean(GroovyRestClient)
    }

}
