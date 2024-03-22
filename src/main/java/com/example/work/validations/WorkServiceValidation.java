package com.example.work.validations;

import com.example.work.dto.request.WorkRequest;
import com.example.work.exception.WorkException;
import com.example.work.message.WorkMessage;

public class WorkServiceValidation {

    public static void validateWorkRequest(WorkRequest workRequest) {
        if (workRequest == null) {
            throw new WorkException(WorkMessage.INTERNAL_SERVER_ERROR);
        }
        if (workRequest.getWorkName() == null || workRequest.getWorkName().isEmpty()) {
            throw new WorkException(WorkMessage.INVALID_WORK_NAME);
        }
    }
}
