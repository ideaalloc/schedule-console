package com.xiaobai.polestar.scheduleconsole;

import com.xiaobai.polestar.scheduleconsole.models.Schedules;
import com.xiaobai.polestar.scheduleconsole.services.JobService;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.Map;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-10-17
 */
@SpringBootApplication
@EnableScheduling
public class Application {
    static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(final JobService jobService) {

        return arg -> {
            final Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            final List<Schedules> schedules = jobService.getAllEnabled();
            schedules.forEach(schedule -> {
                final Map<JobDetail, Trigger> jobDetailTriggerMap = jobService.scheduleJob(schedule);
                jobDetailTriggerMap.forEach((job, trigger) -> {
                    try {
                        scheduler.scheduleJob(job, trigger);
                    } catch (SchedulerException e) {
                        LOGGER.error("scheduler error", e);
                        throw new RuntimeException(e);
                    }
                });
            });
        };

    }
}
