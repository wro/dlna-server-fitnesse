package config

import spock.lang.Specification

class ConfigurationHolderSpec extends Specification {

    def holder = new ConfigurationHolder()
    def slurper = new ConfigSlurper()

    def "loads default config"() {
        expect:
        holder.config == slurper.parse(DefaultConfig)
        holder.config.rest.url == 'http://localhost:8080/'
    }

    def "overrides default config"() {
        given:
        holder = new ConfigurationHolder(getClass().getResource('/config.properties').path)

        expect:
        holder.config == slurper.parse(DefaultConfig).merge(slurper.parse(getClass().getResource('/config.properties')))
        holder.config.rest.url == 'http://new-url:8080'
    }
}
