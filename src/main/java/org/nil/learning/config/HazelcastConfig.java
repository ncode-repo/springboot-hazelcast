package org.nil.learning.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

@Configuration
@PropertySource("classpath:hazelcast.properties")
public class HazelcastConfig {
	@Value("${hazelcast.management.center}")
	String manCenterUrl;
	
	@Bean
	public Config hazelCastConfig() {
		Config conf = new Config();
		conf.setInstanceName("hazelcast-instance")
			.addMapConfig(new MapConfig().setName("tickets-cache")
										 .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
										 .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(2000));
		ManagementCenterConfig managementCenterConfig = new ManagementCenterConfig();
		managementCenterConfig.setUrl(manCenterUrl).setEnabled(true).setUpdateInterval(5);
		conf.setManagementCenterConfig(managementCenterConfig);
		return conf;
	}
}
