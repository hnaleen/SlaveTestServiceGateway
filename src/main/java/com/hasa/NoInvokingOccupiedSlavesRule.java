package com.hasa;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.AvailabilityPredicate;
import com.netflix.loadbalancer.PredicateBasedRule;

/**
 * - TestServiceGateway -
 * @author Hasantha Alahakoon 
 */
public class NoInvokingOccupiedSlavesRule extends PredicateBasedRule
{
  IClientConfig config;

  @Override public void initWithNiwsConfig(IClientConfig clientConfig)
  {
    this.config = clientConfig;
  }

  @Override public AbstractServerPredicate getPredicate()
  {
    return new AvailabilityPredicate(this, config);
  }
}
