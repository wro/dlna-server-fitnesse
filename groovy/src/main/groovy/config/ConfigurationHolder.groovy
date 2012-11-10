package config

class ConfigurationHolder {
    ConfigObject config

    private ConfigSlurper slurper = new ConfigSlurper()

    ConfigurationHolder() {
        config = slurper.parse(DefaultConfig)
    }

    ConfigurationHolder(String configLocation) {
        this()
        config.merge(slurper.parse(new File(configLocation).toURL()))
    }
}
