package com.homecode.mc_project.twitter.to.kafka.service.listener;

import com.homecode.mc_project.twitter.to.kafka.service.runner.impl.TwitterKafkaStreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
public class TwitterKafkaStatusListener  extends StatusAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStreamRunner.class);

    @Override
    public void onStatus(Status status){
        LOG.info("Twitter status whit text {}",status.getText());
    }
}
