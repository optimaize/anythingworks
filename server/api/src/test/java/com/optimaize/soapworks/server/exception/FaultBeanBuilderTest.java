package com.optimaize.soapworks.server.exception;

import com.optimaize.soapworks.common.soap.exception.Blame;
import com.optimaize.soapworks.server.soap.exception.FaultBean;
import com.optimaize.soapworks.server.soap.exception.FaultBeanBuilder;
import com.optimaize.soapworks.server.soap.exception.Retry;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FaultBeanBuilderTest {

    @Test
    public void testBlame() throws Exception {
        FaultBean faultBean = FaultBeanBuilder
                .blame(Blame.CLIENT)
                .faultCause("InvalidInput")
                .errorCode(2200)
//                .defaultMessage()
                .message("Invalid input")
                .retrySameServer(Retry.no())
                .retryOtherServers(Retry.no())
                .problemReported(false);

        assertEquals(faultBean.getBlame(), Blame.CLIENT);
        assertEquals(faultBean.getFaultCause(), "InvalidInput");
        assertEquals(faultBean.getErrorCode(), 2200);
        assertEquals(faultBean.getMessage(), "Invalid input");
        assertEquals(faultBean.getRetrySameServer(), Retry.no());
        assertEquals(faultBean.getRetryOtherServers(), Retry.no());
        assertEquals(faultBean.isProblemReported(), false);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void serverCodeForClientProblem() throws Exception {
        FaultBean faultBean = FaultBeanBuilder
                .blame(Blame.CLIENT)
                .faultCause("InvalidInput")
                .errorCode(1000)                 // <========== wrong code
                .message("Invalid input")
                .retrySameServer(Retry.no())
                .retryOtherServers(Retry.no())
                .problemReported(false);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void reportedForClientProblem() throws Exception {
        FaultBean faultBean = FaultBeanBuilder
                .blame(Blame.CLIENT)
                .faultCause("InvalidInput")
                .errorCode(2200)
                .message("Invalid input")
                .retrySameServer(Retry.no())
                .retryOtherServers(Retry.no())
                .problemReported(true);
    }

}