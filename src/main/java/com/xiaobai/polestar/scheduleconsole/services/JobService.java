package com.xiaobai.polestar.scheduleconsole.services;

import com.google.common.collect.ImmutableMap;
import com.xiaobai.polestar.scheduleconsole.constants.Constants;
import com.xiaobai.polestar.scheduleconsole.jobs.CommandJob;
import com.xiaobai.polestar.scheduleconsole.models.Schedules;
import com.xiaobai.polestar.scheduleconsole.repositories.ScheduleRepository;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class JobService {
    static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    @Autowired
    ScheduleRepository scheduleRepository;

    @Transactional
    public List<Schedules> getAllEnabled() {
        return scheduleRepository.findAll(true);
    }

    public Map<JobDetail, Trigger> scheduleJob(Schedules schedule) {
        JobDetail job = JobBuilder.newJob(CommandJob.class)
                .withIdentity(schedule.getName(), Constants.DEFAULT_GROUP_NAME)
                .usingJobData(Constants.COMMAND, schedule.getCommand())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(schedule.getName() + "Trigger", Constants.DEFAULT_GROUP_NAME)
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(schedule.getCron()))
                .build();
        return ImmutableMap.of(job, trigger);
    }

    @Transactional
    public Long addJob(final String name, final String cron, final String command) {
        return scheduleRepository.insertSchedule(name, cron, command, true);
    }
}
