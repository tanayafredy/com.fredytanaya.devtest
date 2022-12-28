package com.fredytanaya.devtest.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fredytanaya.devtest.Model.Tbtest;
import com.fredytanaya.devtest.Service.TbtestService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class TbtestController {
    @Autowired
    TbtestService tbtestService;

    // CREATE
    @RequestMapping(value = "/tbtest", method = RequestMethod.POST)
    public ResponseEntity<?> tbtestPOST(@RequestBody Tbtest param, HttpServletRequest request) {
        try {
            Integer remotePort = request.getRemotePort();
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yymmddhhmmss");
            String strDate = dateFormat.format(date);
            Long newId = Long.parseLong(strDate + String.valueOf(remotePort)); //set remote port as part of id for identify creator
            param.setId(newId);
            Tbtest response = tbtestService.CreateTest(param);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ
    @RequestMapping(value = "/tbtest", method = RequestMethod.GET)
    public ResponseEntity<?> tbtestGET(@RequestParam Long id, HttpServletRequest request) {
        try {
            Integer remotePort = request.getRemotePort();
            if (!checkId(id, remotePort)) {
                return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
            } else {
                Tbtest response = tbtestService.GetTestById(id);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // UPDATE
    @RequestMapping(value = "/tbtest", method = RequestMethod.PUT)
    public ResponseEntity<?> tbtestPUT(@RequestBody Tbtest param, HttpServletRequest request) {
        try {
            Integer remotePort = request.getRemotePort();
            if (!checkId(param.getId(), remotePort)) {
                return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
            } else {
                Tbtest response = tbtestService.UpdateTest(param.getId(), param);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    @RequestMapping(value = "/tbtest", method = RequestMethod.DELETE)
    public ResponseEntity<?> tbtestDELETE(@RequestParam Long id, HttpServletRequest request) {
        try {
            Integer remotePort = request.getRemotePort();
            if (!checkId(id, remotePort)) {
                return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
            } else {
                tbtestService.DeleteTest(id);
                return new ResponseEntity<>("Delete success " + id, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // CHECK ID with remote port
    private boolean checkId(long id, Integer port) {
        boolean result = false;
        String idString = Long.toString(id);
        String checkIdString = idString.substring(12);
        String checkPortString = Integer.toString(port);
        if (checkIdString.equals(checkPortString)) {
            result = true;
        }
        return result;
    }

}
