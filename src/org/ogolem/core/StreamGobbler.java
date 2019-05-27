/**
Copyright (c) 2009-2010, J. M. Dieterich and B. Hartke
              2010-2012, J. M. Dieterich
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
package org.ogolem.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Used if some System calls produce either output on the command line or errors.
 * @author Johannes Dieterich
 * @version 2012-11-05
 */
public class StreamGobbler extends Thread{
    
    private static final boolean DEBUG = true;
    private final InputStream is;
    final String type;
    private final List<String> llData = new LinkedList<>();

    public StreamGobbler(InputStream is, String type) {
        this.is = is;
        this.type = type;
    }

    @Override
    public void run() {
        try (final BufferedReader br =
                   new BufferedReader(new InputStreamReader(is))) {
            String line;
            //if(br.ready()){
               while ((line = br.readLine()) != null) {
                    addToList(line);
                }
	    /*} else {
                // else: nothing in there
                if(DEBUG){
                    System.out.println("DEBUG: Nothing in stream " + type);
                }
            }*/
        } catch (IOException ioe) {
            ioe.printStackTrace(System.err);
        }
    }

    private void addToList(final String s){
        llData.add(s);
    }

    public String[] getData(){
        
        final String[] sa = new String[llData.size()];
        for(int i = 0; i < sa.length; i++) {
            sa[i] = llData.get(i);
        }

        return sa;
    }
}