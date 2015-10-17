package com.xiaobai.polestar.scheduleconsole.controllers;

import com.google.common.collect.ImmutableMap;
import com.xiaobai.polestar.scheduleconsole.models.ScheduleVO;
import com.xiaobai.polestar.scheduleconsole.services.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.POST, value = "/schedules")
    public ResponseEntity<Map<String, Long>> addJob(@RequestBody ScheduleVO scheduleVO) {
        LOGGER.info("Start adding schedule");
        final Long id = jobService.addJob(scheduleVO.getName(), scheduleVO.getCron(), scheduleVO.getCommand());
        return new ResponseEntity<>(ImmutableMap.of("id", id), HttpStatus.OK);
    }
}
