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
    static int minutes = 0;

    static public int calculateWaitTime(ArrayDeque<Integer> ticketsQueue, int position) {
        minutes = 0;
        //dynamicPosition tracks the position of the person at the queried position
        int dynamicPosition = position;
        int maxPosition = ticketsQueue.size();

        //checks whether the person at dynamicPosition has gotten all their tickets
        while (!isPositionValueCompleted(ticketsQueue, dynamicPosition)) {
            int poppedVar = ticketsQueue.pop();

            //if the variable being popped's value is 0, make maxPosition smaller and do not re-add
            if (poppedVar <= 1) {
                maxPosition -= 1;
                dynamicPosition -= 1;
                minutes++;
            }

            //otherwise, add it to the back decremented (while adding to the minute counter)
            else {
                ticketsQueue.addLast(poppedVar - 1);
                minutes += 1;

                //if the index of the queried item is 0 upon popping, push his value back to the top (
                if (dynamicPosition == 0) {
                    dynamicPosition = maxPosition - 1;
                }

                //otherwise, subtract value as he advances in the queue
                else {
                    dynamicPosition -= 1;
                }
            }
            System.out.println(ticketsQueue);
        }
        return minutes;
    }

    static boolean isPositionValueCompleted(ArrayDeque<Integer> ticketsQueue, int position) {
        boolean completed = false;
        if (position == 0 && ticketsQueue.getFirst() <= 1){
            minutes+=1;
            completed = true;
        }
        return completed;
    }
}

