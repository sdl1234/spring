package com.sdl.service.test;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.PowerMockUtils;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MockServiceImpl.class)
public class MockServiceImplTest extends TestCase {

    public void testMock() {


        Calendar instance = Calendar.getInstance();
        instance.set(2011,Calendar.JULY - 1,30);
        PowerMockito.mockStatic(Calendar.class);
        Mockito.when(Calendar.getInstance()).thenReturn(instance);
        MockServiceImpl mockService = new MockServiceImpl();
        String s = mockService.get();
        System.out.println("s = " + s);

        System.out.println("Calendar.YEAR = " + Calendar.YEAR);

    }
}