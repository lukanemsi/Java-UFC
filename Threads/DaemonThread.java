package com.company.Threads;

import java.time.LocalTime;


public class DaemonThread implements Runnable
{
    private String state = " generating in files";
    @Override
    public void run()
    {

        while (true)
        {
            System.out.println(LocalTime.now() + state);
            try {
				// 2000 mills cota iyo
                Thread.sleep(20);
            } catch (InterruptedException e)
            {
                System.err.println(LocalTime.now() + " State changed");

                state = " generating out files";
            }
        }

    }
}
