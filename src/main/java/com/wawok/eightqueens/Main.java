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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Brian Wawok on 11/17/2015.
 */

public class Main {

    public static void main(String[] args) {

        final List<Solution> validSolutions = new ArrayList<>();
        final Queue<Solution> solutionsInProgress = new LinkedList<>();

        //seed with an empty solution
        solutionsInProgress.add(new Solution());

        while (solutionsInProgress.peek() != null) {
            final Solution partialSolution = solutionsInProgress.remove();
            final byte currentMaxRank = partialSolution.maxRank();
            for (final Solution solution : computeValidSolutionsForRank((byte) (currentMaxRank + 1), partialSolution)) {
                if (solution.maxRank() == 7) {
                    //made it through all 8 ranks, solution!
                    validSolutions.add(solution);
                } else {
                    //still more work to do, throw it back on the queue
                    solutionsInProgress.add(solution);
                }
            }
        }


        System.out.println("Found a total of " + validSolutions.size() + " valid solutions. They are:");
        for (Solution solution : validSolutions) {
            System.out.println(solution.toString() + "\n");
        }

    }


    public static List<Solution> computeValidSolutionsForRank(final byte rank, final Solution baseSolution) {
        final List<Solution> validSolutions = new ArrayList<>();
        for (byte file = 0; file < 8; file++) {
            if (baseSolution.canPlace(rank, file)) {
                validSolutions.add(baseSolution.setQueen(rank, file));
            }
        }

        return validSolutions;
    }


}
