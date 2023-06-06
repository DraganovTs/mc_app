package com.homecode.mc_project;


import com.homecode.mc_project.init.StreamInitializer;
import com.homecode.mc_project.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterToKafkaApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaApplication.class);

    private final StreamInitializer streamInitializer;
    private final StreamRunner streamRunner;
    public TwitterToKafkaApplication(StreamInitializer streamInitializer, StreamRunner streamRunner) {
        this.streamInitializer = streamInitializer;
        this.streamRunner = streamRunner;
    }

    public static void main(String[] args) {SpringApplication.run(TwitterToKafkaApplication.class,args);}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("App working fine");
        streamInitializer.init();
        streamRunner.start();

    }
}