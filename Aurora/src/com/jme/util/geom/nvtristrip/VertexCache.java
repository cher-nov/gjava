/*
 * Copyright (c) 2003-2006 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors 
 *   may be used to endorse or promote products derived from this software 
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.jme.util.geom.nvtristrip;

import java.util.Arrays;


class VertexCache {

    int[] entries;
    int numEntries;
    
    public VertexCache() {
        this(16);
    }
    
    public VertexCache(int size) {
        numEntries = size;
        entries = new int[numEntries];
        clear();
    }
    
    public boolean inCache(int entry) {
        for(int i = 0; i < numEntries; i++)
        {
            if(entries[i] == entry)
            {
                return true;
            }
        }
        return false;
    }
    
    public int addEntry(int entry) {
        int removed;
        
        removed = entries[numEntries - 1];
        
        //push everything right one
        for(int i = numEntries - 2; i >= 0; i--)
           {
            entries[i + 1] = entries[i];
        }
        
        entries[0] = entry;
        
        return removed;
    }

    public void clear() {
        Arrays.fill(entries,-1);
    }
    
    public int at(int index) {
        return entries[index];
    }
    
    public void set(int index, int value) {
        entries[index] = value;
    }
        
    public void copy(VertexCache inVcache)
    {
        for(int i = 0; i < numEntries; i++)
           {
            inVcache.set(i, entries[i]);
        }
    }

}
