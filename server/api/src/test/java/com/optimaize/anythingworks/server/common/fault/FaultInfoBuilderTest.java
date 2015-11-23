package com.optimaize.anythingworks.server.common.fault;

import com.optimaize.anythingworks.common.fault.faultinfo.Blame;
import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import com.optimaize.anythingworks.common.fault.faultinfo.Retry;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 *
 */
public class FaultInfoBuilderTest {

    @Test
    public void testBlame() throws Exception {
        FaultInfo faultInfo = FaultInfoBuilder
                .blame(Blame.CLIENT)
                .faultCause("InvalidInput")
                .applicationErrorCode(null)
                .message("Invalid input")
                .retrySameLocation(Retry.no())
                .retryOtherLocations(Retry.no())
                .incidentId(null);

        assertEquals(faultInfo.getBlame(), Blame.CLIENT);
        assertEquals(faultInfo.getFaultCause(), "InvalidInput");
        assertEquals(faultInfo.getApplicationErrorCode().orNull(), null);
        assertEquals(faultInfo.getMessage(), "Invalid input");
        assertEquals(faultInfo.getRetrySameLocation().orNull(), Retry.no());
        assertEquals(faultInfo.getRetryOtherLocations().orNull(), Retry.no());
        assertEquals(faultInfo.getIncidentId().orNull(), null);
    }

}