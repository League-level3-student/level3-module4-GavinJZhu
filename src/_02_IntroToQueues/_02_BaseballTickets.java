/*
 * Copyright (c) 2020, <GiacomoSorbi> All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. The views and conclusions contained in the
 * software and documentation are those of the authors and should not be
 * interpreted as representing official policies, either expressed or implied,
 * of the FreeBSD Project.
 */

package _02_IntroToQueues;

import java.util.ArrayDeque;

/*
 * Complete the calculateWaitTime() method here!
 * Instructions are in the BaseBallTicketsTest class.
 */

public class _02_BaseballTickets {

    public static int calculateWaitTime( ArrayDeque<Integer> ticketsQueue, int position ) {
        //i dont know what im doing
        int minutes = 0;
        int dynamicPosition = position;
        int maxPosition = position;
        while (!isPositionValueCompleted(ticketsQueue, dynamicPosition)) {
            int poppedVar = ticketsQueue.pop();
            if (poppedVar == 0){
                ticketsQueue.pop();
                maxPosition =-1;
            }
            else {
                ticketsQueue.pop();
                ticketsQueue.push(poppedVar-1);
                minutes += 1;
                dynamicPosition =-1;
            }
        }

        System.out.println(ticketsQueue.getFirst());

//        int numOfTickets = ticketsQueue.getFirst();
//            for (int i = 0; i<ticketsQueue.size(); i++) {
//                int poppedVar = ticketsQueue.pop();
//                if (poppedVar != 0) {
//                    ticketsQueue.push(poppedVar - 1);
//                    minutes += 1;
//                    //System.out.println(poppedVar);
//                }
//                if (numOfTickets>0 && i == ticketsQueue.size()-1){
//                    numOfTickets -=1;
//                    i=0;
//                }
//            }
        return minutes;
    }
    static boolean isPositionValueCompleted(ArrayDeque<Integer> ticketsQueue, int position){
        boolean completed = false;
        int currentIndex = 0;
        for (int element : ticketsQueue) {
            if (currentIndex == position) {
//                if (element == 0){
//                    completed = true;
//                }
                completed = element == 0;
                break;
            }
            currentIndex++;
        }
        return completed;
    }
}

