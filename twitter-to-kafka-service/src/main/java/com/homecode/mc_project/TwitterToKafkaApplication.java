package com.homecode.mc_project;


import com.homecode.mc_project.app.config.TwitterToKafkaServicesConfigData;
import com.homecode.mc_project.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class TwitterToKafkaApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaApplication.class);
    private final TwitterToKafkaServicesConfigData twitterToKafkaServicesConfigData;

    private final StreamRunner streamRunner;
    public TwitterToKafkaApplication(TwitterToKafkaServicesConfigData twitterToKafkaServicesConfigData, StreamRunner streamRunner) {
        this.twitterToKafkaServicesConfigData = twitterToKafkaServicesConfigData;
        this.streamRunner = streamRunner;
    }

    public static void main(String[] args) {SpringApplication.run(TwitterToKafkaApplication.class,args);}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("App working fine");
        LOG.info(Arrays.toString(twitterToKafkaServicesConfigData.getTwitterKeywords().toArray(new String[] {}) ));
        LOG.info(Arrays.toString(twitterToKafkaServicesConfigData.getWelcomeMessage().toArray(new String[0])));
        streamRunner.start();

    }
}