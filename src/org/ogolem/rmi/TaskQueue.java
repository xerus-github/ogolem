/**
Copyright (c) 2010-2014, J. M. Dieterich
              2015-2016, J. M. Dieterich and B. Hartke
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.

    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.

    * All advertising materials mentioning features or use of this software
      must display the following acknowledgement:

      This product includes software of the ogolem.org project developed by
      J. M. Dieterich and B. Hartke (Christian-Albrechts-University Kiel, Germany)
      and contributors.

    * Neither the name of the ogolem.org project, the University of Kiel
      nor the names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR(S) ''AS IS'' AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.ogolem.rmi;

import java.io.Serializable;
import java.util.List;
import org.ogolem.helpers.Tuple3D;

/**
 * A no-queue queue. ;-) Might make sense to have this class at a later point in
 * time.
 * @author Johannes Dieterich
 * @version 2016-01-25
 */
public final class TaskQueue<T> implements Serializable {

    private static final long serialVersionUID = (long) 201411201;
    private final Job<T> job;

    TaskQueue(final Job<T> computeJob){
        job =  computeJob;
    }

    Task<T> getNextTask(){
        return job.nextTask();
    }
    
    List<Task<T>> nextInitTasks(final int maxTasks, final int noProxies){
        return job.nextInitTasks(maxTasks, noProxies);
    }
    
    Tuple3D<Boolean, Long, Integer> nextGlobOptChunk(final int maxTasks){
        return job.nextGlobOptChunk(maxTasks);
    }

    RMICodes.JOBSTATE submitResult(final Result<T> res){
        return job.submitResult(res);
    }

    boolean queueWaiting(){
        return job.jobWaiting();
    }

    boolean queueClosed(){
        return job.jobFinished();
    }
    
    List<T> mergePools(final int noOfAssocResults, 
            final List<T> clientPool, final int maxStructsBack, final long lastStart){
        return job.mergePools(noOfAssocResults, clientPool, maxStructsBack, lastStart);
    }
}
