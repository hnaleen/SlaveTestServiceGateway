package com.hasa;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * - TestServiceGateway -
 * @author Hasantha Alahakoon 
 */
public class LoadBalancerConfiguration
{
  @Autowired IClientConfig ribbonClientConfig;

  @Bean public IRule ribbonRule(IClientConfig config)
  {
    NoInvokingOccupiedSlavesRule loadBalanceRule = new NoInvokingOccupiedSlavesRule();
    loadBalanceRule.initWithNiwsConfig(config);
    return loadBalanceRule;
  }

}
