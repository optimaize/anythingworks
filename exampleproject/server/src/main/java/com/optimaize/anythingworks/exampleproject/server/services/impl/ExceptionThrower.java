package com.optimaize.anythingworks.exampleproject.server.services.impl;

import com.optimaize.anythingworks.common.fault.exceptions.ServiceException;
import com.optimaize.anythingworks.server.rest.fault.exception.AccessDeniedServiceExceptions;
import com.optimaize.anythingworks.server.rest.fault.exception.BadRequestServiceExceptions;
import com.optimaize.anythingworks.server.rest.fault.exception.InternalServerErrorServiceExceptions;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * A service that performs business logic.
 */
@Service
public class ExceptionThrower {

    public String throwException(String apiKey, String exceptionType, Integer exceptionChance) throws ServiceException {
        int finalExceptionChance = (exceptionChance == null) ? 100 : exceptionChance;
        Random r = new Random();
        int randomInt = r.nextInt(101); //returns 0-100 (101 is exclusive)
        if (finalExceptionChance != 0 && randomInt <= finalExceptionChance) {
            if (exceptionType != null) {
                if (exceptionType.equals("InvalidInput")) {
                    throw BadRequestServiceExceptions.invalidInput("Something's not quite right!");

                } else if (exceptionType.equals("NoSuchAccount")) {
                    throw AccessDeniedServiceExceptions.accountUnknown(apiKey);

                } else if (exceptionType.equals("InternalServerError")) {
                    throw InternalServerErrorServiceExceptions.internalServerError("No one knows what happened.");
                }
            }

            throw BadRequestServiceExceptions.invalidInput("Unsupported requested exception type: >>>" + exceptionType + "<<<!");
        } else {
            return "OK";
        }
    }

}
