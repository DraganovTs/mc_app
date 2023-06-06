package com.homecode.mc_project.listener;

import com.homecode.mc_project.app.config.KafkaConfigData;
import com.homecode.mc_project.config.service.KafkaProducer;
import com.homecode.mc_project.runner.impl.TwitterKafkaStreamRunner;
import com.homecode.mc_project.transformer.TwitterStatusToAvroTransformer;
import com.mc.project.kafka.avro.model.TwitterAvroModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
public class TwitterKafkaStatusListener  extends StatusAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStreamRunner.class);
    private final KafkaConfigData kafkaConfigData;

    private final KafkaProducer<Long, TwitterAvroModel> kafkaProducer;

    private final TwitterStatusToAvroTransformer twitterStatusToAvroTransformer;

    public TwitterKafkaStatusListener(KafkaConfigData kafkaConfigData, KafkaProducer<Long, TwitterAvroModel> kafkaProducer, TwitterStatusToAvroTransformer twitterStatusToAvroTransformer) {
        this.kafkaConfigData = kafkaConfigData;
        this.kafkaProducer = kafkaProducer;
        this.twitterStatusToAvroTransformer = twitterStatusToAvroTransformer;
    }

    @Override
    public void onStatus(Status status){
        LOG.info("Twitter status whit text {}",status.getText());TwitterAvroModel twitterAvroModel = twitterStatusToAvroTransformer.getTwitterAvroModelFromStatus(status);
        kafkaProducer.send(kafkaConfigData.getTopicName(), twitterAvroModel.getUserId(), twitterAvroModel);
    }
}
