package com.xiaobai.polestar.scheduleconsole.controllers;

import com.google.common.collect.ImmutableMap;
import com.xiaobai.polestar.scheduleconsole.models.ScheduleVO;
import com.xiaobai.polestar.scheduleconsole.models.Schedules;
import com.xiaobai.polestar.scheduleconsole.services.JobService;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
@RestController
@RequestMapping("/v1")
public class ApiController {
    static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    JobService jobService;

    @Autowired
    Scheduler scheduler;

    @RequestMapping(method = RequestMethod.POST, value = "/schedules")
    public ResponseEntity<Map<String, Long>> addJob(@RequestBody ScheduleVO scheduleVO) {
        LOGGER.info("Start adding schedule");
        final Long id = jobService.addJob(scheduleVO.getName(), scheduleVO.getCron(), scheduleVO.getCommand());
        final Map<JobDetail, Trigger> jobDetailTriggerMap = jobService.scheduleJob(new Schedules(id, scheduleVO.getName(), scheduleVO.getCron(), scheduleVO.getCommand(), true, new Date()));
        jobDetailTriggerMap.forEach((job, trigger) -> {
            try {
                scheduler.scheduleJob(job, trigger);
            } catch (SchedulerException e) {
                LOGGER.error("scheduler error", e);
                throw new RuntimeException(e);
            }
        });
        return new ResponseEntity<>(ImmutableMap.of("id", id), HttpStatus.OK);
    }
}
