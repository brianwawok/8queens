/*
 * This software is licensed under the MIT license, quoted below.
 * [https://opensource.org/licenses/MIT]
 *
 * Copyright (c) 2015 Brian Wawok <bwawok@gmail.com>
 *
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.wawok.eightqueens;

/**
 * Created by Brian Wawok on 11/17/2015.
 */

public class Solution {

    private final byte[] ranks;


    //new blank Solution
    public Solution() {
        this.ranks = new byte[0];
    }

    //internal copy constructor
    private Solution(byte[] ranks) {
        this.ranks = ranks;
    }


    //return true if we can place a queen here
    public boolean canPlace(byte rank, byte file) {

        if (rank < ranks.length){
            //we already filled this rank, no go
            return false;
        }

        for (byte x = 0; x < ranks.length; x++) {
            byte currentQueen = ranks[x];
            if (currentQueen == file) {
                //same file as current queen, no good
                return false;
            }

            if (Math.abs(rank - x) == Math.abs(file - currentQueen)) {
                //hit a diagonal
                return false;
            }
        }
        return true;
    }

    //returns a new solution with a new queen set
    public Solution setQueen(byte rank, byte file) {
        byte[] newArray = new byte[ranks.length + 1];
        System.arraycopy(ranks, 0, newArray, 0, ranks.length);
        newArray[rank] = file;
        return new Solution(newArray);
    }


    public byte maxRank() {
        return (byte) (ranks.length - 1);
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder(72); //micro-opt the SB size!
        for (byte rank : ranks) {
            result.append("\n");
            for (int file = 0; file < 8; file++) {
                if (rank == file) {
                    result.append("Q");
                } else {
                    result.append("-");
                }
            }
        }

        return result.toString();
    }
}
